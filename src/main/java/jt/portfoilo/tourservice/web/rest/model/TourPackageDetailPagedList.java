package jt.portfoilo.tourservice.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by JT on 3/31/2019
 */
public class TourPackageDetailPagedList extends PageImpl<TourPackageDetailDTO> {
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public TourPackageDetailPagedList(@JsonProperty("content") List<TourPackageDetailDTO> content,
									  @JsonProperty("number") int number,
									  @JsonProperty("size") int size,
									  @JsonProperty("totalElements") Long totalElements,
									  @JsonProperty("pageable") JsonNode pageable,
									  @JsonProperty("last") boolean last,
									  @JsonProperty("totalPages") int totalPages,
									  @JsonProperty("sort") JsonNode sort,
									  @JsonProperty("first") boolean first,
									  @JsonProperty("numberOfElements") int numberOfElements) {
		
		super(content, PageRequest.of(number, size), totalElements);
	}
	
	public TourPackageDetailPagedList(List<TourPackageDetailDTO> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}
	
	public TourPackageDetailPagedList(List<TourPackageDetailDTO> content) {
		super(content);
	}
}
