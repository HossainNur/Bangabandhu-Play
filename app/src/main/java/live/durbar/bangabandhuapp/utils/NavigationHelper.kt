package live.durbar.bangabandhuapp.utils


import com.google.android.material.appbar.AppBarLayout
import kotlin.concurrent.Volatile

class NavigationHelper private constructor() {
    var title: String? = null
    var shortTitle: String? = null
    var description: String? = null
    var image: String? = null
    var currentFragment: String
    var appBarLayout: AppBarLayout? = null

    init {
        currentFragment = Constants.HOME_FRAGMENT
    }

    companion object {
        @Volatile
        var instanceNavHelper: NavigationHelper? = null
            get() {
                if (field == null) {
                    synchronized(NavigationHelper::class.java) {
                        if (field == null) {
                            field = NavigationHelper()
                        }
                    }
                }
                return field
            }
            private set
    }
}
