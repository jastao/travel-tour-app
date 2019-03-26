package jt.portfoilo.tourservice.bootstrap;

import jt.portfoilo.tourservice.entities.*;
import jt.portfoilo.tourservice.repository.DailyTourDetailRespository;
import jt.portfoilo.tourservice.repository.PackagePricingRepository;
import jt.portfoilo.tourservice.repository.TourPackageDetailRepository;
import jt.portfoilo.tourservice.repository.TourPackageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;

/**
 * Craeted by JT on 3/21/2019
 */
@Component
public class DataInitializer implements CommandLineRunner {
	
	private final PackagePricingRepository packagePricingRepository;
	private final DailyTourDetailRespository dailyTourDetailRespository;
	private final TourPackageDetailRepository tourPackageDetailRepository;
	private final TourPackageRepository tourPackageRepository;
	
	public DataInitializer( PackagePricingRepository packagePricingRepository, DailyTourDetailRespository dailyTourDetailRespository,
							TourPackageDetailRepository tourPackageDetailRepository, TourPackageRepository tourPackageRepository) {
		this.packagePricingRepository = packagePricingRepository;
		this.dailyTourDetailRespository = dailyTourDetailRespository;
		this.tourPackageDetailRepository = tourPackageDetailRepository;
		this.tourPackageRepository = tourPackageRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		initializeData();
	}
	
	private void initializeData() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date offerStartDate = sdf.parse("10/01/2019");
		Date offerEndDate = sdf.parse("11/01/2019");
		
		TourPackageDetail tourPackageDetail
				= new TourPackageDetail("", "Grand Canyon", 3, "Los Angeles",
				offerStartDate, offerEndDate, DayOfWeek.MONDAY);
		
		PackagePricing pricingForTwo = new PackagePricing(RoomTypeEnum.TWO_PER_ROOM, 538.0);
		PackagePricing pricingForThree = new PackagePricing(RoomTypeEnum.THREE_PER_ROOM, 70.0);
		
		DailyTourDetail firstDay = new DailyTourDetail(1, "LAX - Hotel");
		DailyTourDetail secondDay = new DailyTourDetail(2, "Disneyland");
		
		tourPackageDetail.addPackagePricingResources(pricingForTwo);
		tourPackageDetail.addPackagePricingResources(pricingForThree);
		tourPackageDetail.addDailyTourDetails(firstDay);
		tourPackageDetail.addDailyTourDetails(secondDay);
		
		TourPackage tourPackage = new TourPackage("L1", "Los Angeles - Amusement Park");
		tourPackageDetail.setTourPackage(tourPackage);
		tourPackageDetailRepository.save(tourPackageDetail);
	}
}
