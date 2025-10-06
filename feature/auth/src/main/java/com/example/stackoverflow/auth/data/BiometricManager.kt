package com.example.stackoverflow.auth.data

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.stackoverflow.auth.domain.BiometricResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class BiometricManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val biometricManager = BiometricManager.from(context)

    fun isBiometricAvailable(): Boolean {
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            else -> false
        }
    }

    fun isBiometricEnrolled(): Boolean {
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> false
            else -> false
        }
    }

    suspend fun authenticate(activity: FragmentActivity): BiometricResult {
        return suspendCancellableCoroutine { continuation ->
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Use your fingerprint or face to authenticate")
                .setNegativeButtonText("Cancel")
                .build()

            val biometricPrompt = BiometricPrompt(activity, 
                ContextCompat.getMainExecutor(activity),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        val result = when (errorCode) {
                            BiometricPrompt.ERROR_USER_CANCELED -> BiometricResult.Cancelled
                            BiometricPrompt.ERROR_LOCKOUT -> BiometricResult.ErrorLockout
                            BiometricPrompt.ERROR_LOCKOUT_PERMANENT -> BiometricResult.ErrorLockoutPermanent
                            else -> BiometricResult.Error(errorCode, errString.toString())
                        }
                        if (continuation.isActive) {
                            continuation.resume(result)
                        }
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        if (continuation.isActive) {
                            continuation.resume(BiometricResult.Success)
                        }
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        if (continuation.isActive) {
                            continuation.resume(BiometricResult.Failed)
                        }
                    }
                }
            )

            continuation.invokeOnCancellation {
                // Handle cancellation if needed
            }

            biometricPrompt.authenticate(promptInfo)
        }
    }
}