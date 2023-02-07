package com.example.myapplication.ui.list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.ui.destinations.InfScreenDestination
import com.example.myapplication.ui.inf.InfViewModel
import com.example.myapplication.ui.list.Item.MyListItem
import com.example.myapplication.ui.veiwModel.MyViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun ListScreen(
    navigator: DestinationsNavigator,
    //viewModel: ListViewModel = hiltViewModel()
) {
    val viewModel=  koinViewModel<MyViewModel>()
    val infViewModel= koinViewModel<InfViewModel>()
    val state by koinViewModel<MyViewModel>().screenState.collectAsStateWithLifecycle()
    var index:Int=0

    //val state by viewModel.screenState.collectAsStateWithLifecycle()
//    val context = LocalContext.current
   LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {

                is ListEvent.GoToInf->{index= it.index
                    println(index)
                   infViewModel.updateState { it.copy(id=index) }
                   navigator.navigate(InfScreenDestination.route)
                }

            }
        }
    }
    ListScreenContent(state,viewModel::onClickItem)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun ListScreenContent(
    state:
    ListState,
     onClickItem: (Int) -> Unit,
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
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    itemsIndexed(state.listOfItems) { index, itemData ->
                        MyListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = remember(index) { { onClickItem(index) } })
                                .animateItemPlacement(),
                            data = itemData
                        )
                    }
                }
            }
        }
    }
}