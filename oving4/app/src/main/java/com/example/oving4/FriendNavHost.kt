package com.example.oving4

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.oving4.ui.theme.LeksjonFriendTheme

@Composable
fun FriendNavHost (
    modifier: Modifier = Modifier,
    viewModel: FriendViewModel = viewModel(),
) {
    val nav = rememberNavController()
    val friends by viewModel.friends.collectAsStateWithLifecycle()

    NavHost(nav, startDestination = FriendList, modifier = modifier) {
        composable<FriendList> {
            FriendListScreen(
                friends = friends,
                onFriendsClick = { friend ->
                    val index = friends.indexOf(friend)
                    nav.navigate(FriendDetail(index))
                },
                onAddClick = {nav.navigate(AddFriend)},
            )
        }
        composable<AddFriend> {
            AddFriendScreen(viewModel = viewModel, onDone = {nav.popBackStack()})
        }
        composable<FriendDetail> {entry ->
            val route: FriendDetail = entry.toRoute()
            val friend = friends[route.index]
            FriendDetailScreen(
                friend = friend,
                onBack = {nav.popBackStack()},
                onEdit = {
                    viewModel.editFriendByIndex(route.index)
                    nav.navigate(EditFriend(route.index))
                })
        }
        composable<EditFriend> { entry ->
            val route: EditFriend = entry.toRoute()
            EditFriendScreen(
                index = route.index,
                viewModel = viewModel,
                onDone = { nav.popBackStack() }
            )
        }
    }
}


@Preview(showBackground = true, heightDp = 640)
@Composable
fun FriendNavHostPreview() {
    LeksjonFriendTheme {
        FriendNavHost()
    }
}