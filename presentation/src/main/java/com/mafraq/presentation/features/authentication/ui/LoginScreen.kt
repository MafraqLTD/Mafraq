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
import com.mafraq.presentation.features.authentication.event.LoginEvent
import com.mafraq.presentation.features.authentication.listener.LoginInteractionListener
import com.mafraq.presentation.features.authentication.state.AuthUiState
import com.mafraq.presentation.features.authentication.ui.components.AuthContainer
import com.mafraq.presentation.features.authentication.ui.components.CredentialErrorState
import com.mafraq.presentation.features.authentication.ui.components.HaveAnAccount
import com.mafraq.presentation.features.authentication.viewmodel.AuthViewModel
import com.mafraq.presentation.navigation.destinations.navigateToLoginProfile
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.string


@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    navController: NavController,
    navigateToHome: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    val state: AuthUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: AuthEvent? by viewModel.event.collectAsState(null)
    val listener: LoginInteractionListener = viewModel

    Content(state = state, listener = listener)

    (event as? LoginEvent)?.Listen { currentEvent ->
        when (currentEvent) {
            LoginEvent.OnLogin -> navigateToHome()
            LoginEvent.OnNavigateToRegister -> navigateToRegister()
            LoginEvent.OnNavigateToLoginProfile -> navController.navigateToLoginProfile()
        }
    }
}


@Composable
private fun Content(
    state: AuthUiState = AuthUiState(),
    listener: LoginInteractionListener = LoginInteractionListener.PreviewInstance
) {
    AuthContainer(
        title = R.string.welcome.string,
        description = R.string.login_description.string,
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

        Spacer.Large()

        AppButton(
            text = R.string.login.string,
            onClick = {
                listener.onLogin()
                focusManager.clearFocus()
            },
            loading = state.isLoading,
            enabled = listener.validateLoginFields().isValid,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Medium()

        HaveAnAccount(
            text = R.string.dont_have_an_account.string,
            clickableText = R.string.register.string,
            isLoading = state.isLoading,
            onClick = listener::onNavigateToRegister,
        )

        Spacer.Large()

        CredentialErrorState(state.error?.message) {
            listener.validateLoginFields().isInvalid || state.isError
        }
    }

}


@Composable
@Preview(showBackground = true)
private fun LoginScreenPreview() {
    ColumnPreview { Content() }
}
