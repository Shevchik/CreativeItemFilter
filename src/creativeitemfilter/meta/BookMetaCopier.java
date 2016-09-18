package creativeitemfilter.meta;

import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import creativeitemfilter.utils.StringUtils;

public class BookMetaCopier implements MetaCopier<BookMeta> {

	public static final BookMetaCopier INSTANCE = new BookMetaCopier();

	private BookMetaCopier() {
	}

	@Override
	public ItemMeta copyValidMeta(BookMeta oldMeta, Material material) {
		BookMeta newBookMeta = (BookMeta) Bukkit.getItemFactory().getItemMeta(material);
		if (oldMeta.hasAuthor()) {
			newBookMeta.setAuthor(StringUtils.clampString(oldMeta.getAuthor(), 16));
		}
		if (oldMeta.hasTitle()) {
			newBookMeta.setTitle(StringUtils.clampString(oldMeta.getTitle()));
		}
		if (oldMeta.hasGeneration()) {
			newBookMeta.setGeneration(oldMeta.getGeneration());
		}
		if (oldMeta.hasPages()) {
			newBookMeta.setPages(oldMeta.getPages().stream().map(string -> StringUtils.clampString(string, Short.MAX_VALUE / 2)).collect(Collectors.toList()));
		}
		return newBookMeta;
	}

}
