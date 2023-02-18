package com.suatzengin.forgotpassword.common


import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.suatzengin.forgotpassword.R
import java.util.concurrent.Executor

object BiometricPromptUtil {

    fun createBiometricPrompt(
        activity: FragmentActivity,
        processSuccess: () -> Unit
    ): BiometricPrompt{
        val executor: Executor = ContextCompat.getMainExecutor(activity)

        val callback = object: BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(activity, "$errString", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                processSuccess()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(activity, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }
        return BiometricPrompt(activity,executor, callback)
    }

    fun createPromptInfo(activity: FragmentActivity): PromptInfo{
        return PromptInfo.Builder().apply {
            setTitle(activity.getString(R.string.prompt_info_title))
            setSubtitle(activity.getString(R.string.prompt_info_subtitle))
            setNegativeButtonText(activity.getString(R.string.prompt_info_cancel))
        }.build()
    }
}