package com.example.miprimercompose_davidroldan.framework.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.miprimercompose_davidroldan.R
import com.example.miprimercompose_davidroldan.common.Constantes
import com.example.miprimercompose_davidroldan.ui.theme.MiPrimerCompose_DavidRoldanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pantalla()
        }
    }
}

@Composable
fun Pantalla(
    viewModel: MainViewModel = hiltViewModel(),
) {
    ContenidoPantalla(viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContenidoPantalla(
    viewModel: MainViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    MiPrimerCompose_DavidRoldanTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.medium_padding))
            ) {
                var editmode by rememberSaveable { mutableStateOf(false) }

                Column(modifier = Modifier.align(Alignment.Center)) {

                    val uiState by viewModel.uiState.collectAsState()

                    NameField(editmode, uiState.simpson.nombre) {
                        viewModel.handleEvent(
                            MainEvent.OnNameChange(it)
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
                    EdadSlider(editmode, uiState.simpson.edad) {
                        viewModel.handleEvent(
                            MainEvent.OnEdadChange(it)
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
                    ProfesionSpinner(
                        editmode,
                        uiState.simpson.profesion
                    ) {
                        viewModel.handleEvent(
                            MainEvent.OnProfesionChange(it)
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
                    VivoCheckbox(editmode, uiState.simpson.vivo) {
                        viewModel.handleEvent(
                            MainEvent.OnVivoChange(it)
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
                    GenderRadioGroup(editmode, uiState.simpson.genero) {
                        viewModel.handleEvent(
                            MainEvent.OnGeneroChange(it)
                        )
                    }
                    if (editmode) {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
                        CrudButtons(
                            uiState.updatedelete ?: false,
                            { viewModel.handleEvent(MainEvent.AddSimpson()) },
                            { viewModel.handleEvent(MainEvent.UpdateSimpson()) },
                            { viewModel.handleEvent(MainEvent.DeleteSimpson()) })
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
                    NavigationButtons(
                        uiState.noanterior ?: false,
                        uiState.nosiguiente ?: false,
                        { viewModel.handleEvent(MainEvent.GetSiguienteSimpson()) },
                        { viewModel.handleEvent(MainEvent.GetAnteriorSimpson()) })
                }

                Column(modifier = Modifier.align(Alignment.TopEnd)) {
                    Switch(
                        checked = editmode,
                        onCheckedChange = {
                            editmode = it
                        },
                        thumbContent = {
                            if (editmode) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.RemoveRedEye,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                )
                            }
                        }
                    )
                }
                LaunchedEffect(viewModel.uiState) {
                    viewModel.uiState.collect { uiState ->
                        if (uiState.error != null) {
                            snackbarHostState.showSnackbar(
                                message = uiState.error,
                                actionLabel = Constantes.DISMISS,
                                duration = SnackbarDuration.Short
                            )
                            viewModel.handleEvent(MainEvent.ErrorVisto)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NameField(editmode: Boolean, name: String, onNameChange: (String) -> Unit) {
    if (editmode)
        TextField(
            value = name,
            onValueChange = onNameChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = stringResource(id = R.string.nombre)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    Icons.Filled.PermIdentity,
                    contentDescription = stringResource(id = R.string.nombre)
                )
            },
        )
    else
        Text(
            name.ifEmpty { stringResource(id = R.string.nombre) },
            style = typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)

        )

}

@Composable
fun EdadSlider(editmode: Boolean, edad: Int, onEdadChange: (Float) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.edad) + edad)
        }
        if (editmode) {
            Slider(
                value = edad.toFloat(),
                onValueChange = onEdadChange,
                modifier = Modifier.fillMaxWidth(),
                valueRange = 0f..100f,
                steps = 100,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                )
            )
        }
    }
}

@Composable
fun ProfesionSpinner(editmode: Boolean, profesion: String, onProfesionChange: (String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.profesion),
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.medium_padding))
            )

            var expanded by remember { mutableStateOf(false) }
            val options = stringArrayResource(id = R.array.Profesiones)

            Row(
                modifier = Modifier.clickable { expanded = true },
            ) {
                Text(
                    profesion.ifEmpty { stringResource(id = R.string.desconocida) },
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.small_padding))
                )
                if (editmode) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
            if (editmode) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                expanded = false
                                onProfesionChange(label)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun VivoCheckbox(editmode: Boolean, vivo: Boolean, onVivoChange: (Boolean) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.big_padding))
            .padding(horizontal = dimensionResource(id = R.dimen.medium_padding)),
        horizontalArrangement = Arrangement.Center
    ) {
        if (editmode) {
            Checkbox(
                checked = vivo,
                onCheckedChange = null,
                modifier = Modifier.toggleable(
                    value = vivo,
                    onValueChange = onVivoChange,
                    role = Role.Checkbox
                )
            )
        }
        Text(
            if (!editmode) {
                if (vivo) stringResource(id = R.string.vivo) else stringResource(id = R.string.muerto)
            } else {
                stringResource(id = R.string.vivo)
            },
            style = typography.bodyLarge,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.medium_padding))
        )
    }
}


@Composable
fun GenderRadioGroup(editmode: Boolean, gender: String, onGenderChange: (String) -> Unit) {
    val options = stringArrayResource(id = R.array.Gender)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.medium_padding)),
        horizontalArrangement = Arrangement.Center
    ) {
        if (editmode) {
            options.forEach { text ->
                Row(
                    Modifier
                        .selectable(
                            selected = (text == gender),
                            onClick = { onGenderChange(text) }
                        )
                        .padding(horizontal = dimensionResource(id = R.dimen.small_padding))
                ) {
                    RadioButton(
                        selected = (text == gender),
                        onClick = null
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.medium_padding))
                    )
                }
            }
        } else {
            Text(
                gender.ifEmpty { stringResource(id = R.string.noespecificado) }
            )
        }
    }
}


@Composable
fun CrudButtons(
    updatedelete: Boolean,
    clickadd: () -> Unit,
    clickupdate: () -> Unit,
    clickdelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.small_padding)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ExtendedFloatingActionButton(
            text = { Text(stringResource(id = R.string.add)) },
            icon = { Icon(Icons.Default.Add, contentDescription = null) },
            onClick = clickadd
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding)))
        ExtendedFloatingActionButton(
            text = { Text(stringResource(id = R.string.update)) },
            icon = { Icon(Icons.Default.Update, contentDescription = null) },
            onClick = { if (updatedelete) clickupdate() },
            containerColor = if (!updatedelete) Color.Gray else MaterialTheme.colorScheme.primaryContainer
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding)))
        ExtendedFloatingActionButton(
            text = { Text(stringResource(id = R.string.delete)) },
            icon = { Icon(Icons.Default.Delete, contentDescription = null) },
            onClick = { if (updatedelete) clickdelete() },
            containerColor = if (!updatedelete) Color.Gray else MaterialTheme.colorScheme.primaryContainer
        )
    }
}

@Composable
fun NavigationButtons(
    noanterior: Boolean,
    nosiguiente: Boolean,
    clicksiguiente: () -> Unit,
    clickanterior: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.big_padding)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ExtendedFloatingActionButton(
            content = { Icon(Icons.Default.ArrowBack, contentDescription = null) },
            onClick = { if (!noanterior) clickanterior() },
            containerColor = if (noanterior) Color.Gray else MaterialTheme.colorScheme.primaryContainer
        )
        ExtendedFloatingActionButton(
            content = { Icon(Icons.Default.ArrowForward, contentDescription = null) },
            onClick = { if (!nosiguiente) clicksiguiente() },
            containerColor = if (nosiguiente) Color.Gray else MaterialTheme.colorScheme.primaryContainer
        )
    }
}


//@Preview
//@PreviewScreenSizes
//@Composable
//fun GreetingPreview() {
//    ContenidoPantalla()
//}