package org.hurricanegames.creativeitemfilter.handler.meta;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.inventory.meta.BookMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.StringUtils;

public class BookMetaCopier implements MetaCopier<BookMeta> {

	public static final BookMetaCopier INSTANCE = new BookMetaCopier();

	protected BookMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, BookMeta oldMeta, BookMeta newMeta) {
		if (oldMeta.hasAuthor()) {
			newMeta.setAuthor(StringUtils.filterValidCharacters(StringUtils.clampString(oldMeta.getAuthor(), configuration.getBookAuthorMaxLength())));
		}
		if (oldMeta.hasTitle()) {
			newMeta.setTitle(StringUtils.filterValidCharacters(StringUtils.clampString(oldMeta.getTitle(), configuration.getBookTitleMaxLength())));
		}
		if (oldMeta.hasGeneration()) {
			newMeta.setGeneration(oldMeta.getGeneration());
		}
		if (oldMeta.hasPages()) {
			int bookPagesMaxLength = configuration.getBookPagesMaxLength();
			newMeta.setPages(
					oldMeta.getPages().stream()
							.map(string -> StringUtils.filterValidCharacters(StringUtils.clampString(string, bookPagesMaxLength)))
							.limit(configuration.getBookPagesMaxCount())
							.collect(Collectors.toList())
			);
		}
	}
}
