package com.darkube.silentScheduler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.darkube.silentScheduler.viewmodels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewSchedule(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    val timePickerStateHorizontal = rememberTimePickerState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { viewModel.closeDialogStatus() },
        containerColor = Color.Transparent,
        modifier = modifier
            .fillMaxHeight()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(size = 20.dp)
                )
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clip(shape = RoundedCornerShape(size = 16.dp))
                        .background(color = Color(0xFF1e293b))
                        .padding(horizontal =  16.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = "Start Time : 09:00am",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                        shape = RoundedCornerShape(size = 10.dp),
                    ) {
                        Text(
                            text = "Change",
                            color = MaterialTheme.colorScheme.background,
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clip(shape = RoundedCornerShape(size = 16.dp))
                        .background(color = Color(0xFF1e293b))
                        .padding(horizontal =  16.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = "End Time   : 09:30am",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                        shape = RoundedCornerShape(size = 10.dp),
                    ) {
                        Text(
                            text = "Change",
                            color = MaterialTheme.colorScheme.background,
                        )
                    }
                }
            }
            TimeInput(state = timePickerStateHorizontal)
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {
                        viewModel.closeDialogStatus()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                    ),
                    shape = RoundedCornerShape(size = 10.dp),
                ) {
                    Text(
                        text = "Save",
                        color = MaterialTheme.colorScheme.background,
                    )
                }
                Button(
                    onClick = {
                        viewModel.closeDialogStatus()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFFf43f5e),
                    ),
                    shape = RoundedCornerShape(size = 10.dp),
                ) {
                    Text(
                        text = "Close",
                        color = Color.White,
                    )
                }
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInput(
    state: TimePickerState,
) {
    TimePicker(
        state = state,
        layoutType = TimePickerLayoutType.Vertical,
        colors = TimePickerDefaults.colors(
            clockDialColor = Color(0xFF1e293b),
            clockDialUnselectedContentColor = Color.White,
            clockDialSelectedContentColor = MaterialTheme.colorScheme.background,
            selectorColor = Color.White,
            periodSelectorUnselectedContentColor = Color.White,
            periodSelectorSelectedContainerColor = Color(0xFFbfdbfe),
            timeSelectorUnselectedContainerColor = Color(0xFF1e293b),
            timeSelectorUnselectedContentColor = Color(0xFFe2e8f0),
            timeSelectorSelectedContainerColor = Color(0xFFe2e8f0),
        )
    )
}
