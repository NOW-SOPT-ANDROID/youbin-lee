import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.sopt.now.compose.R

sealed class BottomNavItem(
    val title: Int, val icon: ImageVector, val route: String
) {
    data object Home : BottomNavItem(R.string.nav_home, Icons.Filled.Home, "HOME")
    data object Search : BottomNavItem(R.string.nav_search, Icons.Filled.Search, "SCREEN")
    data object MyPage : BottomNavItem(R.string.nav_my_page, Icons.Filled.Person, "MY_PAGE")

    companion object {
        val items = listOf(Home, Search, MyPage)
    }
}

//const val HOME = "HOME"
//const val SCREEN = "SCREEN"
//const val MY_PAGE = "MY_PAGE"
