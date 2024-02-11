package com.darkube.silentScheduler.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkube.silentScheduler.ui.components.LoadingAnimation
import com.darkube.silentScheduler.ui.components.SilentAnimation
import com.darkube.silentScheduler.ui.components.SpeakerAnimation
import com.darkube.silentScheduler.R
import com.darkube.silentScheduler.types.Time
import com.darkube.silentScheduler.types.TimePeriod
import com.darkube.silentScheduler.types.TimeRange
import com.darkube.silentScheduler.ui.components.NewSchedule
import com.darkube.silentScheduler.viewmodels.MainViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = MainViewModel(),
) {
    Scaffold(
        floatingActionButton = { BottomButton(viewModel = viewModel) },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart,
        ) {
            Box(
                modifier = modifier
                    .padding(all = 10.dp)
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(size = 20.dp)),
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFF1f2937)),
                ) {
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background,
                        ),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 40.dp,
                            bottomEnd = 20.dp,
                        )
                    ) {
                        GetCurrentStatusAnimation()
                    }
                    Box(
                        modifier = modifier
                            .padding(12.dp)
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(size = 20.dp))
                            .background(color = MaterialTheme.colorScheme.background),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.back_ground),
                            contentDescription = "Background Image",
                            contentScale = ContentScale.FillWidth,
                            modifier = modifier
                                .matchParentSize()
                                .alpha(0.2f),
                        )
                        Column(
                            modifier = modifier
                                .padding(horizontal = 16.dp)
                                .matchParentSize()

                        ) {
                            SilenceSchedule()
                            if (viewModel.openDialog) {
                                NewSchedule(viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GetCurrentStatusAnimation() {
    val isSilent = true
    if (isSilent) {
        SilentAnimation()
    } else {
        SpeakerAnimation()
    }
}

@Composable
fun SilenceSchedule() {
    val isLoading = false
    val schedules = mutableListOf(
        TimeRange(
            start = Time(hours = 9, minutes = 0, period = TimePeriod.AM),
            end = Time(hours = 9, minutes = 30, period = TimePeriod.AM),
        ),
        TimeRange(
            start = Time(hours = 10, minutes = 0, period = TimePeriod.AM),
            end = Time(hours = 10, minutes = 30, period = TimePeriod.AM),
        ),
        TimeRange(
            start = Time(hours = 11, minutes = 0, period = TimePeriod.AM),
            end = Time(hours = 11, minutes = 45, period = TimePeriod.AM),
        ),
        TimeRange(
            start = Time(hours = 12, minutes = 0, period = TimePeriod.AM),
            end = Time(hours = 12, minutes = 40, period = TimePeriod.AM),
        ),
        TimeRange(
            start = Time(hours = 2, minutes = 0, period = TimePeriod.PM),
            end = Time(hours = 3, minutes = 35, period = TimePeriod.PM),
        ),
        TimeRange(
            start = Time(hours = 4, minutes = 10, period = TimePeriod.PM),
            end = Time(hours = 4, minutes = 35, period = TimePeriod.PM),
        ),
        TimeRange(
            start = Time(hours = 5, minutes = 15, period = TimePeriod.PM),
            end = Time(hours = 6, minutes = 0, period = TimePeriod.PM),
        ),
    )
    if (isLoading) {
        LoadingAnimation()
    } else {
        LazyColumn {
            itemsIndexed(schedules) { index, timeRange ->
                GlassCard(
                    topMargin = if (index == 0) 16.dp else 0.dp,
                    timeRange = timeRange,
                )
            }
        }
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    topMargin: Dp = 0.dp,
    timeRange: TimeRange,
) {
    val ptSansFontFamily = FontFamily(
        Font(R.font.ptsans_regular, FontWeight.Normal),
    )
    Box(
        modifier = modifier
            .padding(top = topMargin, bottom = 16.dp)
            .fillMaxWidth()
            .height(160.dp)
            .clip(shape = RoundedCornerShape(size = 20.dp))
    ) {
        Box(
            modifier = modifier
                .matchParentSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFffffff).copy(alpha = 0.05f),
                            Color(0xFFffffff).copy(alpha = 0.1f),
                        )
                    )
                )
                .blur(radius = 10.dp)
                .clip(shape = RoundedCornerShape(size = 20.dp))
        ) {
        }
        Column(
            modifier = modifier
                .padding(horizontal = 12.dp, vertical = 16.dp)
                .matchParentSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Scheduled",
                    color = Color.White,
                    fontFamily = ptSansFontFamily,
                    fontSize = 17.sp,
                )
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(id = R.drawable.sound_off),
                        contentDescription = "Time Symbol",
                        tint = Color.White,
                        modifier = modifier
                            .height(15.dp)
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        text = "Time: $timeRange",
                        color = Color.White,
                        fontFamily = ptSansFontFamily,
                        fontSize = 15.sp,
                    )
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Duration: ${timeRange.durationToString()}",
                    color = Color.White,
                    fontFamily = ptSansFontFamily,
                    fontSize = 17.sp,
                )
                FilledTonalIconButton(
                    onClick = { },
                    modifier = modifier
                        .padding(end = 8.dp)
                        .size(35.dp),
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                    shape = RoundedCornerShape(size = 10.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.trash_bin),
                        contentDescription = "Time Symbol",
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun BottomButton(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    FilledTonalIconButton(
        onClick = { viewModel.openDialogStatus() },
        modifier = modifier
            .padding(horizontal = 10.dp)
            .size(50.dp),
        colors = IconButtonDefaults.filledTonalIconButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.background
        ),
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add Schedule",
            modifier = modifier.size(40.dp),
        )
    }
}
