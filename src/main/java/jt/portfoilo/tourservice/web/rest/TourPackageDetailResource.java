package jt.portfoilo.tourservice.web.rest;

import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailDTO;
import jt.portfoilo.tourservice.services.impl.TourPackageDetailService;
import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailPagedList;
import jt.portfoilo.tourservice.web.rest.util.HeaderUtil;
import jt.portfoilo.tourservice.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;


/**
 * Created by JT on 3/24/2019
 */
@RestController
@RequestMapping("/api/v1")
public class TourPackageDetailResource {
	
	private Logger logger = LoggerFactory.getLogger(TourPackageDetailResource.class);

	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 10;
	private static final String ENTITY_NAME = "tourPackageEntity";

	private final TourPackageDetailService tourPackageDetailService;
	
	public TourPackageDetailResource(TourPackageDetailService tourPackageDetailService) {
		this.tourPackageDetailService = tourPackageDetailService;
	}

	@PostMapping("/tour-package-detail")
	public ResponseEntity<TourPackageDetailDTO> createTourPackageDetail(@RequestBody TourPackageDetailDTO tourPackageDetailDTO) throws URISyntaxException {

		logger.debug("REST request to save TourPackageDetail: {}", tourPackageDetailDTO);
		if(tourPackageDetailDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tourPackageDetail cannot be created.")).body(null);
		}
		TourPackageDetailDTO result = tourPackageDetailService.save(tourPackageDetailDTO);
		return ResponseEntity.created(new URI("/tour-package-detail/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	@PutMapping("/tour-package-detail")
	public ResponseEntity<TourPackageDetailDTO> updateTourPackageDetail(@RequestBody TourPackageDetailDTO tourPackageDetailDTO) throws URISyntaxException {

		logger.debug("REST request to update TourPackageDetail: {}", tourPackageDetailDTO);
		if(tourPackageDetailDTO.getId() == null) {
			return createTourPackageDetail(tourPackageDetailDTO);
		}
		TourPackageDetailDTO result = this.tourPackageDetailService.save(tourPackageDetailDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tourPackageDetailDTO.getId().toString()))
				.body(result);
	}

	@DeleteMapping("/tour-package-detail/{id}")
	public ResponseEntity<Void> removeTourPackageDetail(@PathVariable UUID tourPackageId) {

		logger.debug("REST request to delete TourPackageDetail: {}", tourPackageId);
		this.tourPackageDetailService.delete(tourPackageId);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, String.valueOf(tourPackageId))).body(null);
	}

	@GetMapping("/tour-package-details")
	public ResponseEntity<TourPackageDetailPagedList> getTourPackages(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
																	  @RequestParam(value = "pageSize", required = false) Integer pageSize) {

		logger.debug("REST request to retrieve all TourPackageDetail");
		TourPackageDetailPagedList packages;
		
		if(pageNumber == null || pageNumber < 0) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		
		if(pageSize == null || pageSize < 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		
		packages = this.tourPackageDetailService.tourPackages("", PageRequest.of(pageNumber, pageSize));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(packages, "/api/v1/tour-package-details");
		return new ResponseEntity<>(packages, headers, HttpStatus.OK);
	}
	
	@GetMapping("/tour-package-detail/tourpackage/{tour_code}")
	public ResponseEntity<TourPackageDetailDTO> getTourPackageDetailByTourCode(@PathVariable("tour_code") String tourCode) throws Exception {

		logger.debug("REST request to retrieve TourPackageDetailDTO by tour code: {}", tourCode);
		TourPackageDetailDTO tourPackageDetailDTO = this.tourPackageDetailService.tourPackageByTourCode(tourCode);
		return ResponseEntity.ok().headers(new HttpHeaders()).body(tourPackageDetailDTO);
	}
	@GetMapping("/tour-package-details/{dep_city}")
	public ResponseEntity<List<TourPackageDetailDTO>> getTourPackageDetailsByDepartureCity(@RequestParam("depart_city") String depCity,
																						   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
																						   @RequestParam(value = "pageSize", required = false) Integer pageSize) {

		logger.debug("REST request to retrieve list of TourPackageDetailDTOs by departure city: {}", depCity);
		Page<TourPackageDetailDTO> packages = this.tourPackageDetailService.tourPackages(depCity, PageRequest.of(pageNumber, pageSize));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(packages, "/api/v1/tour-package-details/" + depCity);
		return new ResponseEntity<>(packages.getContent(), headers, HttpStatus.OK);
	}

}
