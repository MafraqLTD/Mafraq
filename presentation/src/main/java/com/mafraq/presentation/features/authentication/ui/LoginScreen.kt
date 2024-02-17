package com.mafraq.presentation.features.authentication.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppButton
import com.mafraq.presentation.design.components.AppCheckbox
import com.mafraq.presentation.design.components.AppOutlinedTextField
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.features.authentication.event.AuthEvent
import com.mafraq.presentation.features.authentication.event.LoginEvent
import com.mafraq.presentation.features.authentication.listener.LoginInteractionListener
import com.mafraq.presentation.features.authentication.state.AuthUiState
import com.mafraq.presentation.features.authentication.ui.components.AuthContainer
import com.mafraq.presentation.features.authentication.viewmodel.AuthViewModel
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.clickableNoRipple
import com.mafraq.presentation.utils.extensions.string


@Composable
fun LoginScreen(viewModel: AuthViewModel, navController: NavController) {
    val state: AuthUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: AuthEvent? by viewModel.event.collectAsState(null)
    val listener: LoginInteractionListener = viewModel

    Content(state = state, listener = listener)

    (event as? LoginEvent)?.Listen { currentEvent ->
        when (currentEvent) {
            LoginEvent.OnLogin -> {}
            LoginEvent.OnNavigateToRegister -> {}
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
            label = R.string.username.string,
            value = state.username,
            onValueChange = listener::setUsername,
            isError = state.isError,
            errorMessage = state.error?.message,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Medium()

        AppOutlinedTextField(
            label = R.string.password.string,
            value = state.password,
            isPassword = true,
            imeAction = ImeAction.Done,
            onValueChange = listener::setPassword,
            isError = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Small()

        AppCheckbox(
            modifier = Modifier.fillMaxWidth(),
            initialState = state.rememberMe,
            label = R.string.remember_me.string,
            onCheckedChange = listener::setRememberMe
        )

        Spacer.Large()

        AppButton(
            text = R.string.login.string,
            onClick = {
                listener.onLogin()
                focusManager.clearFocus()
            },
            loading = state.isLoading,
            enabled = listener.validateLoginFields(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Medium()

        DoNotHaveAnAccount(state.isLoading, listener::onNavigateToRegister)
    }

}

@Composable
private fun DoNotHaveAnAccount(
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = R.string.dont_have_an_account.string,
            style = MafraqTheme.typography.label,
            color = MafraqTheme.colors.contentTertiary
        )

        Spacer.Small(vertical = false)

        Text(
            text = R.string.register.string,
            style = MafraqTheme.typography.titleSmall,
            color = MafraqTheme.colors.primary,
            modifier = Modifier.clickableNoRipple(
                enabled = !isLoading,
                onClick = onClick
            )
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun LoginScreenPreview() {
    ColumnPreview { Content() }
}
