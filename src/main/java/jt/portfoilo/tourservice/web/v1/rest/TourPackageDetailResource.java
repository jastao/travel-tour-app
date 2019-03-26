package jt.portfoilo.tourservice.web.v1.rest;

import jt.portfoilo.tourservice.service.dto.TourPackageDetailDTO;
import jt.portfoilo.tourservice.service.impl.TourPackageDetailService;
import jt.portfoilo.tourservice.service.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by JT on 3/24/2019
 */
@RestController
@RequestMapping("/api/v1")
public class TourPackageDetailResource {
	
	private Logger logger = LoggerFactory.getLogger(TourPackageDetailResource.class);
	
	private final TourPackageDetailService tourPackageDetailService;
	
	public TourPackageDetailResource(TourPackageDetailService tourPackageDetailService) {
		this.tourPackageDetailService = tourPackageDetailService;
	}
	
	@GetMapping("/show-all-tours")
	public ResponseEntity<List<TourPackageDetailDTO>> getTourPackages(Pageable pageable) {
		
		Page<TourPackageDetailDTO> packages = this.tourPackageDetailService.tourPackages(pageable.getPageNumber(), pageable.getPageSize());
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(packages, "/api/v1/show-all-tours");
		return new ResponseEntity<>(packages.getContent(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/search-by/:tour_code")
	public ResponseEntity<TourPackageDetailDTO> getTourPackageByTourCode(@PathVariable("tour_code") String tourCode) {
		
		TourPackageDetailDTO tourPackage = null;
		try {
			tourPackage = this.tourPackageDetailService.tourPackageByTourCode(tourCode);
		} catch ( Exception ex ) {
			return ResponseEntity.badRequest().body(null);
		}
		
		return ResponseEntity.ok().body(tourPackage);
	}
	@GetMapping("/search-by/:departure_city")
	public ResponseEntity<List<TourPackageDetailDTO>> getTourPackageByDepartureCity(@PathVariable("departure_city") String departCity, Pageable pageable) {
	
		Page<TourPackageDetailDTO> packages = this.tourPackageDetailService.tourPackagesByDepCity(departCity, pageable.getPageNumber(), pageable.getPageSize());
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(packages, "/api/v1/search-by/" + departCity);
		return new ResponseEntity<>(packages.getContent(), headers, HttpStatus.OK);
	}
}
