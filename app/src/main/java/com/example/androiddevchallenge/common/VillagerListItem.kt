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
package com.example.androiddevchallenge.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Villager
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun VillagerItem(villager: Villager, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface),
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            CoilImage(
                data = villager.iconUrl,
                contentDescription = "icon",
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = villager.txtColor)
            )
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {
                val (name, birthday, hobby, hobbyValue, gender) = createRefs()

                Text(
                    text = villager.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(name) {
                        top.linkTo(parent.top)
                    }
                )
                Image(
                    painter = painterResource(
                        id = if (villager.gender == "M") {
                            R.drawable.ic_male
                        } else {
                            R.drawable.ic_female
                        }
                    ),
                    contentDescription = "Gender image",
                    modifier = Modifier
                        .constrainAs(gender) {
                            top.linkTo(name.top)
                            end.linkTo(parent.end)
                        }
                        .width(22.dp),
                )
                Row(
                    modifier = Modifier.constrainAs(birthday) {
                        top.linkTo(name.bottom, 4.dp)
                    },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cake),
                        contentDescription = "Cake image",
                        modifier = Modifier.width(20.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        villager.birthday,
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
                Text(
                    text = "Hobby:",
                    modifier = Modifier.constrainAs(hobby) {
                        top.linkTo(birthday.bottom, margin = 8.dp)
                    }
                )
                Text(
                    text = villager.hobbies.name.toLowerCase().capitalize(),
                    modifier = Modifier
                        .constrainAs(hobbyValue) {
                            baseline.linkTo(hobby.baseline)
                            start.linkTo(hobby.end, 4.dp)
                        },
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}
