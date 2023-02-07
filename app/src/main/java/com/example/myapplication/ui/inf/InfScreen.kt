package com.example.myapplication.ui.inf

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.R
import com.example.myapplication.ui.destinations.InfScreenDestination
import com.example.myapplication.ui.list.ListEvent
import com.example.myapplication.ui.list.ListState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun InfScreen(navigator: DestinationsNavigator,
)
{    val infViewModel= koinViewModel<InfViewModel>()
    infViewModel.getData()
    val state by infViewModel.screenState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        infViewModel.event.collect {
            when (it) {
                is InfEvent.goBack->navigator.navigateUp()

                }

            }
        }

    InfScreenContent(state,infViewModel::onBack)
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun InfScreenContent(
    state: InfState,
    //onClickItem: (Int) -> Unit,
     onBack: () -> Unit,)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.values.name?:"") },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable(onClick = onBack),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            )
        }
    )
    { Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(it),

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
             //   .weight(1f)

                .padding(5.dp),


        ) { Card(
            modifier = Modifier/*.weight(1f)*/,
            shape = MaterialTheme.shapes.large,
            elevation = 10.dp,
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize(0.42f)
                    .height(0.dp)
                    .padding(0.dp),
                model = state.values.image,

                contentDescription = null
            ) {
                it.fitCenter().centerCrop()
            }}
            Card(
                modifier = Modifier.padding(start = 20.dp),
                shape = MaterialTheme.shapes.large,
                elevation = 10.dp,
            ) {
        Column(
            modifier = Modifier
               // .weight(1f)
                .padding(0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            Text(
                text = state.values.species ?: "",
                style = MaterialTheme.typography.h5
            )


            Text(
                text = state.values.status ?: "",
                style = MaterialTheme.typography.h6.copy(Color.Gray)
            )

            Text(
                text = state.values.gender ?: "",
                style = MaterialTheme.typography.h6.copy(Color.Gray)
            )
            Text(
                text = state.values.type ?: "",
                style = MaterialTheme.typography.h6.copy(Color.Gray)
            )


        }
    }}

}}}













/*
class DetailsActivity : AppCompatActivity() {
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var imageView1: ImageView
    private lateinit var title: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        textView2 = findViewById(R.id.textView)
        textView1 = findViewById(R.id.textVw1)
        imageView1 = findViewById(R.id.imageView)
        title = findViewById(R.id.toolbar)
        title.setOnClickListener { returnTo() }
        show()
    }

    companion object {

        const val text1 = "text1"
        const val text2 = "text2"
        const val picture = "1"


    }


    private fun returnTo() {
        finish()
    }

    private fun show() {
        val imagine = intent.getStringExtra(picture)
        Glide
            .with(this)
            .load(imagine)
            .into(this.imageView1)
        val inttext1 = intent.getStringExtra(text1)
        val inttext2 = intent.getStringExtra(text2)
        textView1.text = inttext1
        textView2.text = inttext2
        title.title = intent.getStringExtra(text1)
    }

}
*/