package com.example.myapplication.ui.list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.ui.list.Item.MyListItem
import com.example.myapplication.ui.veiwModel.MyViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun ListScreen(
    navigator: DestinationsNavigator,
    //viewModel: ListViewModel = hiltViewModel()
) {
    val state = koinViewModel<MyViewModel>().name.observeAsState()

    /*val state by viewModel.screenState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is ListScreenEvent.ShowToast -> Toast.makeText(
                    context, it.text, Toast.LENGTH_LONG
                ).show()
                is ListScreenEvent.GoBack -> navigator.navigateUp()
            }
        }
    }*/
    ListScreenContent(state)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun ListScreenContent(
    state: State<List<ListItemData>?>,
    //  onClickItem: (Int) -> Unit,
    // onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },

                )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            /* if (state.isLoading) {
                 CircularProgressIndicator()
             } else {*/
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                itemsIndexed(state.value ?: listOf()) { index, itemData ->
                    MyListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            //  .clickable(onClick = remember(index) { { onClickItem(index) } })
                            .animateItemPlacement(),
                        data = itemData
                    )
                }
            }
        }
    }
}
