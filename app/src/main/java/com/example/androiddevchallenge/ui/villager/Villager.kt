/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.villager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Hobbies
import com.example.androiddevchallenge.model.villagers
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun VillagerDetail(villagerId: Long) {
    val villager = villagers.first { it.id == villagerId }
    Surface {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .background(color = MaterialTheme.colors.surface)
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            ) {
                CoilImage(
                    data = villager.imageUrl, contentDescription = "icon",
                    contentScale = ContentScale.Crop
                )
            }
            Surface(
                Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .background(MaterialTheme.colors.background)
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.surface)
                        .padding(16.dp)
                ) {
                    Text(text = villager.name, style = MaterialTheme.typography.h4)
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_cake),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                            contentDescription = "Cake image",
                            modifier = Modifier.width(20.dp),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            villager.birthday,
                            style = MaterialTheme.typography.subtitle1,
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(text = "Sex")
                                Image(
                                    painter = painterResource(
                                        id = if (villager.gender == "M") {
                                            R.drawable.ic_male
                                        } else {
                                            R.drawable.ic_female
                                        }
                                    ),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                                    contentDescription = "Gender image",
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(40.dp),
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(text = "Hobby")
                                Image(
                                    painter = painterResource(
                                        when (villager.hobbies) {
                                            Hobbies.PLAY -> R.drawable.ic_sports_esports
                                            Hobbies.FASHION -> R.drawable.ic_fashion
                                            Hobbies.FITNESS -> R.drawable.ic_fitness
                                            Hobbies.NATURE -> R.drawable.ic_nature
                                            Hobbies.EDUCATION -> R.drawable.ic_school
                                            Hobbies.MUSIC -> R.drawable.ic_music_note
                                        }
                                    ),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                                    contentDescription = "Hobby image",
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(40.dp),
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(text = "Personality")
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Snooty",
                                    fontSize = 22.sp,
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "\"${villager.catchphrase}\"",
                                textAlign = TextAlign.Center,
                                fontSize = 26.sp,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.W200
                            )
                        }
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(MaterialTheme.colors.background)
            ) {
                Box(modifier = Modifier.padding(end = 16.dp)) {
                    ExtendedFloatingActionButton(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.ic_tent),
                                "Tent icon"
                            )
                        },
                        text = { Text("Adopt Me") },
                        backgroundColor = MaterialTheme.colors.secondary,
                        onClick = { },
                        elevation = FloatingActionButtonDefaults.elevation(8.dp),
                        modifier = Modifier
                            .width(160.dp)
                            .height(45.dp)
                    )
                }
            }
        }
    }
}
