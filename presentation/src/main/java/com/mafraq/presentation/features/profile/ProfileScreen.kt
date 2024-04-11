package com.mafraq.presentation.features.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.buttons.AppButton
import com.mafraq.presentation.design.components.AppContainer
import com.mafraq.presentation.design.components.AppOutlinedTextField
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.string


@Composable
fun ProfileScreen(viewModel: ProfileViewModel, navController: NavController) {
    val state: ProfileUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: ProfileEvent? by viewModel.event.collectAsState(null)
    val listener: ProfileInteractionListener = viewModel

    Content(
        state = state,
        listener = listener
    )

    event?.Listen { currentEvent ->
        when (currentEvent) {
            ProfileEvent.OnLogout -> {}
        }
    }
}


@Composable
private fun Content(
    state: ProfileUiState = ProfileUiState(),
    listener: ProfileInteractionListener = ProfileInteractionListener.PreviewInstance
) {
    AppContainer { focusManager ->
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
            label = R.string.address.string,
            value = state.address,
            onValueChange = listener::setAddress,
            isError = state.isError,
            errorMessage = state.error?.message,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Medium()

        AppOutlinedTextField(
            label = R.string.email.string,
            value = state.email,
            onValueChange = listener::setEmail,
            isError = state.isError,
            errorMessage = state.error?.message,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Medium()

        AppOutlinedTextField(
            label = R.string.fullname.string,
            value = state.fullname,
            onValueChange = listener::setFullname,
            isError = state.isError,
            errorMessage = state.error?.message,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Medium()

        AppOutlinedTextField(
            label = R.string.phone.string,
            value = state.phone,
            onValueChange = listener::setPhone,
            isError = state.isError,
            errorMessage = state.error?.message,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer.Large()

        AppButton(
            text = R.string.save.string,
            onClick = {
                listener.onSave()
                focusManager.clearFocus()
            },
            loading = state.isLoading,
            enabled = listener.validateFields(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun ProfileScreenPreview() {
    ColumnPreview { Content() }
}
