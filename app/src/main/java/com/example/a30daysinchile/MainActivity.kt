package com.example.a30daysinchile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysinchile.ui.theme._30daysInChileTheme
import com.example.a30daysinchile.data.days
import com.example.a30daysinchile.data.Day
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30daysInChileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DayApp()
                }
            }
        }
    }
}

@Composable
fun DayApp() {
    Scaffold (
        topBar = {
            AppTopAppBar()
        }
    ) {
        _ ->
        LazyColumn{
            items(days){
                DayItem(
                    day = it,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }

}

@Composable
fun DayItem(
    day: Day,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ){
            Row{
                DayInformation(
                    dayName = day.name
                )
                Spacer(modifier = Modifier.weight(1f))
                DayItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded}
                )
            }

            DayIcon(
                dayIcon = day.imageResourceId,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
            if (expanded) {
                DayDescription(
                    dayDescription = day.description,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        top = dimensionResource(id = R.dimen.padding_small),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_medium)
                    )
                )
            }

        }
    }

}

@Composable
private fun DayItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector =
            if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}


@Composable
fun DayIcon(
    @DrawableRes dayIcon: Int,
    modifier: Modifier = Modifier
){
    Image(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.image_size))
            .padding(dimensionResource(id = R.dimen.padding_small)),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = dayIcon),
        contentDescription = null
    )
}

@Composable
fun DayInformation(
    @StringRes dayName: Int,

){
    Column(
        modifier = Modifier
    ) {
        Text(
            text = stringResource(id = dayName),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_small))

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(id = R.drawable.chile),
                    contentDescription = null
                )
                Text(
                    text = "Travel Chile",
                )

            }
        },
        modifier = Modifier
    )
}

@Composable
fun DayDescription(
    @StringRes dayDescription: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = stringResource(id = dayDescription),
            style = MaterialTheme.typography.bodyLarge
        )

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _30daysInChileTheme {
        DayApp()
    }
}