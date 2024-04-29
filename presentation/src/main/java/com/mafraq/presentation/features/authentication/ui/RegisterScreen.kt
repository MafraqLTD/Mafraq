package com.mafraq.presentation.features.authentication.ui


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppOutlinedTextField
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.components.buttons.AppButton
import com.mafraq.presentation.features.authentication.event.AuthEvent
import com.mafraq.presentation.features.authentication.event.RegisterEvent
import com.mafraq.presentation.features.authentication.listener.RegisterInteractionListener
import com.mafraq.presentation.features.authentication.state.AuthUiState
import com.mafraq.presentation.features.authentication.ui.components.AuthContainer
import com.mafraq.presentation.features.authentication.ui.components.CredentialErrorState
import com.mafraq.presentation.features.authentication.ui.components.HaveAnAccount
import com.mafraq.presentation.features.authentication.viewmodel.AuthViewModel
import com.mafraq.presentation.navigation.destinations.navigateToLogin
import com.mafraq.presentation.navigation.destinations.navigateToLoginProfile
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.string


@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    navController: NavController,
    navigateToHome: () -> Unit
) {
    val state: AuthUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: AuthEvent? by viewModel.event.collectAsState(null)
    val listener: RegisterInteractionListener = viewModel

    Content(state = state, listener = listener)

    (event as? RegisterEvent)?.Listen { currentEvent ->
        when (currentEvent) {
            RegisterEvent.OnRegister -> navigateToHome()
            RegisterEvent.OnNavigateToLogin -> navController.navigateToLogin()
            RegisterEvent.OnNavigateToLoginProfile -> navController.navigateToLoginProfile()
        }
    }
}


@Composable
private fun Content(
    state: AuthUiState = AuthUiState(),
    listener: RegisterInteractionListener = RegisterInteractionListener.PreviewInstance
) {
    AuthContainer(
        title = R.string.register_title.string,
        description = R.string.register_description.string,
    ) { focusManager ->
        AppOutlinedTextField(
            label = R.string.email.string,
            value = state.email,
            onValueChange = listener::setEmail,
            isError = state.isEmailInvalid,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Medium()

        AppOutlinedTextField(
            label = R.string.password.string,
            value = state.password,
            isPassword = true,
            imeAction = ImeAction.Done,
            onValueChange = listener::setPassword,
            isError = state.isPasswordInvalid,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Medium()

        AppOutlinedTextField(
            label = R.string.confirm_password.string,
            value = state.confirmPassword,
            isPassword = true,
            imeAction = ImeAction.Done,
            onValueChange = listener::setConfirmPassword,
            isError = state.isConfirmPasswordInvalid,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Large()

        AppButton(
            text = R.string.register.string,
            onClick = {
                listener.onRegister()
                focusManager.clearFocus()
            },
            loading = state.isLoading,
            enabled = listener.validateRegisterFields().isValid,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Medium()

        HaveAnAccount(
            text = R.string.have_an_account.string,
            clickableText = R.string.login.string,
            isLoading = state.isLoading,
            onClick = listener::onNavigateToLogin,
        )

        Spacer.Large()

        CredentialErrorState(state.error?.message) {
            listener.validateRegisterFields().isInvalid || state.isError
        }
    }

}


@Composable
@Preview(showBackground = true)
private fun RegisterScreenPreview() {
    ColumnPreview { Content() }
}
