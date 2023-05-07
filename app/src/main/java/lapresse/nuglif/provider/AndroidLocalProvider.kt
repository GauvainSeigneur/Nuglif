package lapresse.nuglif.provider

import android.content.Context
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import lapresse.presentation.provider.LocalProvider
import java.util.Locale
import javax.inject.Inject

class AndroidLocalProvider @Inject constructor(@ApplicationContext private val context: Context) : LocalProvider {

    override fun getLocal(): Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0]
    } else {
        context.resources.configuration.locale
    }
}