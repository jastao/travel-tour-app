package jt.portfoilo.tourservice.web.rest.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by JT on 3/24/2019
 */
public final class PaginationUtil {
	
	private PaginationUtil() {}
	
	public static HttpHeaders generatePaginationHttpHeaders(Page page, String baseUrl) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
		String link = "";
		if(page.getNumber() + 1 < page.getTotalPages()) {
			link += "<" + generateUrl(baseUrl, page.getNumber() + 1, page.getSize()) + ">; rel=\"next\",";
		}
		// prev link
		if(page.getNumber() > 0) {
			link += "<" + generateUrl(baseUrl, page.getNumber() - 1, page.getSize()) + ">; rel=\"prev\",";
		}
		// last and first link
		int lastPage = 0;
		if(page.getTotalPages() > 0) {
			lastPage = page.getTotalPages() - 1;
		}
		link += "<" + generateUrl(baseUrl, lastPage, page.getSize()) + ">; rel=\"last\",";
		link += "<" + generateUrl(baseUrl, 0, page.getSize()) + ">; rel=\"first\"";
		headers.add(HttpHeaders.LINK, link);
		return headers;
		
	}
	
	private static String generateUrl(String baseUrl, int page, int size) {
		return UriComponentsBuilder.fromUriString(baseUrl).queryParam("page", page).queryParam("size", size).toUriString();
	}
}
