package com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Components.Atoms.AppButton
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.Components.Molecules.GameHeader
import com.m1guelgtz.templatecarsapi.Demo.Features.MagicNumber.Presentation.ViewModels.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicNumberScreen(
    viewModel: GameViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Juego: Número Mágico") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {
            // Molecule: Header con vidas y mensajes
            GameHeader(
                lives = uiState.lives,
                message = uiState.message
            )

            // Input Section
            OutlinedTextField(
                value = uiState.userNumber,
                onValueChange = { viewModel.onUserNumberChange(it) },
                label = { Text("Introduce un número (1-100)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.status == GameStatus.PLAYING,
                singleLine = true
            )

            // Atom: Action Button
            AppButton(
                text = "Validar Número",
                onClick = { viewModel.checkNumber() },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.status == GameStatus.PLAYING && uiState.userNumber.isNotEmpty()
            )

            // Reinicio
            if (uiState.status != GameStatus.PLAYING) {
                AppButton(
                    text = "Jugar de nuevo",
                    onClick = { viewModel.startGame() },
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
