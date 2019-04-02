package jt.portfoilo.tourservice.bootstrap;

import jt.portfoilo.tourservice.domain.*;
import jt.portfoilo.tourservice.repositories.DailyTourDetailRespository;
import jt.portfoilo.tourservice.repositories.PackagePricingRepository;
import jt.portfoilo.tourservice.repositories.TourPackageDetailRepository;
import jt.portfoilo.tourservice.repositories.TourPackageInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by JT on 3/21/2019
 */
@Component
public class DefaultDataLoader implements CommandLineRunner {
	
	private final PackagePricingRepository packagePricingRepository;
	private final DailyTourDetailRespository dailyTourDetailRespository;
	private final TourPackageDetailRepository tourPackageDetailRepository;
	private final TourPackageInfoRepository tourPackageInfoRepository;
	
	public DefaultDataLoader(PackagePricingRepository packagePricingRepository,
							 DailyTourDetailRespository dailyTourDetailRespository,
							 TourPackageDetailRepository tourPackageDetailRepository,
							 TourPackageInfoRepository tourPackageInfoRepository) {
		this.packagePricingRepository = packagePricingRepository;
		this.dailyTourDetailRespository = dailyTourDetailRespository;
		this.tourPackageDetailRepository = tourPackageDetailRepository;
		this.tourPackageInfoRepository = tourPackageInfoRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		loadTourPackageData();
	}
	
	private void loadTourPackageData() throws ParseException {
		
		if (tourPackageDetailRepository.count() == 0) {
			
			Set<PackagePricing> packagePricings = new HashSet<PackagePricing>();
			packagePricings.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.TWO_PER_ROOM)
					.roomPrice(538.0)
					.build());
			packagePricings.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.THREE_PER_ROOM)
					.roomPrice(70.0)
					.build());

			Set<DailyTourDetail> dailyTourDetails = new HashSet<DailyTourDetail>();
			dailyTourDetails.add(DailyTourDetail.builder()
					.dayOfTrip(1)
					.description("LAX - Hotel")
					.build());
			dailyTourDetails.add(DailyTourDetail.builder()
					.dayOfTrip(2)
					.description("Disneyland")
					.build());

			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			TourPackageDetail tourPackageDetail
					= TourPackageDetail.builder()
					.departureCity("Los Angeles")
					.departureDateInWeek(DayOfWeek.MONDAY)
					.description("")
					.duration(3)
					.offerStartDate(sdf.parse("10/01/2019"))
					.offerEndDate(sdf.parse("11/01/2019"))
					.tourTitle("Grand Canyon")
					.build();
			
			dailyTourDetails.forEach(item -> item.setTourPackageDetail(tourPackageDetail));
			packagePricings.forEach(item -> item.setTourPackageDetail(tourPackageDetail));
			
			tourPackageDetail.setDailyTourDetails(dailyTourDetails);
			tourPackageDetail.setPackagePricings(packagePricings);
			
			tourPackageDetailRepository.save(tourPackageDetail);
			
			TourPackageInfo tourPackageInfo = TourPackageInfo.builder()
					.cityOfInterest("L1")
					.tourCode("Los Angeles - Amusement Park")
					.tourPackageDetail(tourPackageDetail)
					.build();
			
			tourPackageInfoRepository.save(tourPackageInfo);
			
			tourPackageDetail.setTourPackageInfo(tourPackageInfo);
			
			tourPackageDetailRepository.save(tourPackageDetail);
			
		}
	}
}
