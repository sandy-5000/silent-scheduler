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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
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
import com.darkube.silentScheduler.ui.components.NewSchedule
import com.darkube.silentScheduler.ui.icons.svgIcon
import com.darkube.silentScheduler.viewmodels.MainViewModel

data class TimeRange(val start: String, val end: String)

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
        TimeRange(start = "09:00am", end = "09:30am"),
        TimeRange(start = "10:00am", end = "10:30am"),
        TimeRange(start = "11:00am", end = "11:45am"),
        TimeRange(start = "12:00pm", end = "12:40pm"),
        TimeRange(start = "02:00pm", end = "02:40pm"),
        TimeRange(start = "03:00pm", end = "03:35pm"),
        TimeRange(start = "04:10pm", end = "04:35pm"),
        TimeRange(start = "05:15pm", end = "06:00pm"),
    )
    if (isLoading) {
        LoadingAnimation()
    } else {
        LazyColumn {
            itemsIndexed(schedules) { index, schedule ->
                GlassCard(
                    topMargin = if (index == 0) 16.dp else 0.dp,
                    start = schedule.start,
                    end = schedule.end,
                )
            }
        }
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    silentMessage: String = "Silent Hours",
    topMargin: Dp = 0.dp,
    start: String,
    end: String,
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
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Time Symbol",
                        tint = Color.White,
                        modifier = modifier.height(15.dp),
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        text = "Time: $start - $end",
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
                    text = "Desc: $silentMessage",
                    color = Color.White,
                    fontFamily = ptSansFontFamily,
                    fontSize = 18.sp,
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
            .padding(all = 15.dp)
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
