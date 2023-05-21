package com.muqi.backendsl.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;

public class PageUtil {
    /*
   ThreadLocal 是存储特定于特定线程的数据的方法。将 Page 对象存储在线程局部变量上。Page 对象可能用于保存有关分页数据集的当前页的信息，例如当前页码、页面大小和总页数。这允许通过应用程序中的不同方法访问数据，而不必将其作为方法参数传递，而仅适用于当前线程。
    它被用作线程本地，因此它不会与其他请求冲突。
     */
    private static final ThreadLocal<Page<?>> PAGE_HOLDER = new ThreadLocal<>();
    /*
    为当前线程设置分页数据集的当前页。调用时，它会为调用此方法的线程设置当前页，以便可以通过同一线程调用的其他方法访问它。
    这将在线程局部变量上设置页面对象，并且只能由当前线程/请求访问。
     */
    public static void setCurrentPage(Page<?> page) {
        PAGE_HOLDER.set(page);
    }

    /*
    '?' 这个符号是 Java 泛型的通配符。在这个方法中，'?' 表示不限定具体的数据类型。如果返回的 Page 对象是任意类型的，那么使用这个通配符就能保证该方法在任何类型下都能正常工作。

     */
    public static Page<?> getPage() {
        Page<?> page = PAGE_HOLDER.get();

        if (Objects.isNull(page)) {
            setCurrentPage(new Page<>());
        }
        return PAGE_HOLDER.get();
    }

    public static Long getCurrent() {
        return getPage().getCurrent();
    }

    public static Long getSize() {
        return getPage().getSize();
    }
    /*
   先调用 getCurrent（） 方法来检索当前页码，然后调用 getSize（） 方法来检索页面大小。
    然后，通过从当前页码中-1 然后将其乘以页面大小来计算当前限制。
    从数据源检索数据以检索正确的数据页时，此值用作偏移量。
    用于计算特定页面数据集的起点，通过将页面大小乘以（当前页面 -1）来计算的。
    无需手动计算前偏移量。
     */
    public static Long getLimitCurrent() {
        return (getCurrent() - 1) * getSize();
    }

    public static void remove() {
        PAGE_HOLDER.remove();
    }

}
