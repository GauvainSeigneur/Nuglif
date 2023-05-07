package lapresse.presentation.extension

import java.text.Collator
import java.util.Locale

inline fun <T, R : Comparable<R>> Iterable<T>.sortedWithLocaleBy(
    locale: Locale?,
    crossinline selector: (T) -> R?,
): Iterable<T> = locale?.let {
    sortedWith(compareBy(Collator.getInstance(locale), selector))
} ?: this.sortedBy(selector)
