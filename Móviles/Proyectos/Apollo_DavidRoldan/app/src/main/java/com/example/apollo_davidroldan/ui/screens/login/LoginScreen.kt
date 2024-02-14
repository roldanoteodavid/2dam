package com.example.apollo_davidroldan.ui.screens.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.apollo_davidroldan.R
import com.example.apollo_davidroldan.common.Constantes

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(viewModel, Modifier.align(Alignment.Center))

    }
}

@Composable
fun Login(viewModel: LoginViewModel, modifier: Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        LaunchedEffect(viewModel.uiState) {
            viewModel.uiState.collect { uiState ->
                if (uiState.error != null) {
                    snackbarHostState.showSnackbar(
                        message = uiState.error,
                        actionLabel = Constantes.DISMISS,
                        duration = SnackbarDuration.Short
                    )
                    viewModel.handleEvent(LoginEvent.ErrorVisto)
                }
            }
        }
        Column(modifier = modifier.padding(innerPadding)) {
            UserField(
                state.value.credentials.username
            ) { viewModel.handleEvent(LoginEvent.onUserNameChange(it)) }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
            PasswordField(
                state.value.credentials.password
            ) { viewModel.handleEvent(LoginEvent.onPasswordChange(it)) }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
            LoginButton { viewModel.handleEvent(LoginEvent.Login()) }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
            RegisterButton { viewModel.handleEvent(LoginEvent.Register()) }
        }
    }
}

@Composable
fun RegisterButton(clickregister: () -> Unit) {
    Button(
        onClick = clickregister,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
    ) {
        Text("Register")
    }
}

@Composable
fun LoginButton(clicklogin: () -> Unit) {
    Button(
        onClick = clicklogin,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
    ) {
        Text("Login")
    }
}

@Composable
fun PasswordField(password: String, onPasswordChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun UserField(username: String, onUserNameChange: (String) -> Unit) {
    TextField(
        value = username,
        onValueChange = onUserNameChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("User") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
    )
}

@Preview
@PreviewScreenSizes
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
