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

			/*********************Data Point 1*****************************/

			Set<PackagePricing> packagePricings = new HashSet<PackagePricing>();
			packagePricings.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.SINGLE_ROOM)
					.roomPrice(95.0)
					.build());
			packagePricings.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.TWO_PER_ROOM)
					.roomPrice(95.0)
					.build());
			packagePricings.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.THREE_PER_ROOM)
					.roomPrice(40.0)
					.build());
			packagePricings.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.FOUR_PER_ROOM)
					.roomPrice(40.0)
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
					.duration(2)
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
					.cityOfInterest("Los Angeles - Amusement Park")
					.tourCode("L1")
					.tourPackageDetail(tourPackageDetail)
					.build();
			
			tourPackageInfoRepository.save(tourPackageInfo);
			
			tourPackageDetail.setTourPackageInfo(tourPackageInfo);
			
			tourPackageDetailRepository.save(tourPackageDetail);

			/*********************Data Point 2*****************************/

			Set<PackagePricing> packagePricings2 = new HashSet<PackagePricing>();
			packagePricings2.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.SINGLE_ROOM)
					.roomPrice(428.0)
					.build());
			packagePricings2.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.TWO_PER_ROOM)
					.roomPrice(428.0)
					.build());
			packagePricings2.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.THREE_PER_ROOM)
					.roomPrice(10.0)
					.build());
			packagePricings2.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.FOUR_PER_ROOM)
					.roomPrice(318.0)
					.build());

			Set<DailyTourDetail> dailyTourDetails2 = new HashSet<DailyTourDetail>();
			dailyTourDetails2.add(DailyTourDetail.builder()
					.dayOfTrip(1)
					.description("LAS Pick up")
					.build());
			dailyTourDetails2.add(DailyTourDetail.builder()
					.dayOfTrip(2)
					.description("(A) Hoover Dam - West Rim Grand Canyon Skywalk - Las Vegas, " +
							     "(B) Hoover Dam - South Rim Grand National Park - Las vegas, " +
								 "(C) Antelope Canyon one day tour")
					.build());
			dailyTourDetails2.add(DailyTourDetail.builder()
					.dayOfTrip(3)
					.description("Chocolate Factory - Botanical Cactus Garden - Tanger Outlets - LA")
					.build());
			dailyTourDetails2.add(DailyTourDetail.builder()
					.dayOfTrip(4)
					.description("17 Mile Dr - SF")
					.build());
			dailyTourDetails2.add(DailyTourDetail.builder()
					.dayOfTrip(5)
					.description("UC Berkeley - SF City Tour")
					.build());
			dailyTourDetails2.add(DailyTourDetail.builder()
					.dayOfTrip(6)
					.description("Yosemite National Park - LA/LAX")
					.build());

			TourPackageDetail tourPackageDetail2
					= TourPackageDetail.builder()
					.departureCity("Las Vegas")
					.departureDateInWeek(DayOfWeek.MONDAY)
					.description("Las Vegas/Grand Canyon/SF")
					.duration(6)
					.offerStartDate(sdf.parse("01/01/2019"))
					.offerEndDate(sdf.parse("12/31/2019"))
					.tourTitle("Las Vegas/Grand Canyon/SF")
					.build();

			dailyTourDetails2.forEach(item -> item.setTourPackageDetail(tourPackageDetail2));
			packagePricings2.forEach(item -> item.setTourPackageDetail(tourPackageDetail2));

			tourPackageDetail2.setDailyTourDetails(dailyTourDetails2);
			tourPackageDetail2.setPackagePricings(packagePricings2);

			tourPackageDetailRepository.save(tourPackageDetail2);

			TourPackageInfo tourPackageInfo2 = TourPackageInfo.builder()
					.cityOfInterest("Las Vegas - Grand Canyon - SF")
					.tourCode("VSG1")
					.tourPackageDetail(tourPackageDetail2)
					.build();

			tourPackageInfoRepository.save(tourPackageInfo2);

			tourPackageDetail2.setTourPackageInfo(tourPackageInfo2);

			tourPackageDetailRepository.save(tourPackageDetail2);

			/*********************Data Point 3*****************************/

			Set<PackagePricing> packagePricings3 = new HashSet<PackagePricing>();
			packagePricings3.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.SINGLE_ROOM)
					.roomPrice(418.0)
					.build());
			packagePricings3.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.TWO_PER_ROOM)
					.roomPrice(418.0)
					.build());
			packagePricings3.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.THREE_PER_ROOM)
					.roomPrice(40.0)
					.build());
			packagePricings3.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.FOUR_PER_ROOM)
					.roomPrice(248.0)
					.build());

			Set<DailyTourDetail> dailyTourDetails3 = new HashSet<DailyTourDetail>();
			dailyTourDetails3.add(DailyTourDetail.builder()
					.dayOfTrip(1)
					.description("LAX - Transfer to hotel")
					.build());
			dailyTourDetails3.add(DailyTourDetail.builder()
					.dayOfTrip(2)
					.description("(A) Universal Studio, " +
							"(B) Palm Spring Outlets, " +
							"(C) LA City Tour, " +
							"(D) San Diego City Tour, " +
							"(E) Sea World, " +
							"(F) Disneyland, " +
							"(G) California Adventure, " +
							"(H) Legoland, " +
							"(I) Instagram LA Hot Spots City Tour, " +
							"(J) Whale Watching, Battleship Iowa - Aquarium")
					.build());
			dailyTourDetails3.add(DailyTourDetail.builder()
					.dayOfTrip(3)
					.description("17 Mile Dr - SF")
					.build());
			dailyTourDetails3.add(DailyTourDetail.builder()
					.dayOfTrip(4)
					.description("UC Berkeley - SF City Tour")
					.build());
			dailyTourDetails3.add(DailyTourDetail.builder()
					.dayOfTrip(5)
					.description("Yosemite National Park - LA/LAX")
					.build());


			TourPackageDetail tourPackageDetail3
					= TourPackageDetail.builder()
					.departureCity("Los Angeles")
					.departureDateInWeek(DayOfWeek.MONDAY)
					.description("Theme Park - San Francisco 17 Mile Dr")
					.duration(5)
					.offerStartDate(sdf.parse("06/01/2019"))
					.offerEndDate(sdf.parse("12/31/2019"))
					.tourTitle("Theme Park - San Francisco 17 Mile Dr")
					.build();

			dailyTourDetails3.forEach(item -> item.setTourPackageDetail(tourPackageDetail3));
			packagePricings3.forEach(item -> item.setTourPackageDetail(tourPackageDetail3));

			tourPackageDetail3.setDailyTourDetails(dailyTourDetails3);
			tourPackageDetail3.setPackagePricings(packagePricings3);

			tourPackageDetailRepository.save(tourPackageDetail3);

			TourPackageInfo tourPackageInfo3 = TourPackageInfo.builder()
					.cityOfInterest("Las Vegas - Grand Canyon - SF")
					.tourCode("VSG1")
					.tourPackageDetail(tourPackageDetail3)
					.build();

			tourPackageInfoRepository.save(tourPackageInfo3);

			tourPackageDetail3.setTourPackageInfo(tourPackageInfo3);

			tourPackageDetailRepository.save(tourPackageDetail3);

			/*********************Data Point 4*****************************/

			Set<PackagePricing> packagePricings4 = new HashSet<PackagePricing>();
			packagePricings4.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.SINGLE_ROOM)
					.roomPrice(258.0)
					.build());
			packagePricings4.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.TWO_PER_ROOM)
					.roomPrice(258.0)
					.build());
			packagePricings4.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.THREE_PER_ROOM)
					.roomPrice(0.0)
					.build());
			packagePricings4.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.FOUR_PER_ROOM)
					.roomPrice(178.0)
					.build());

			Set<DailyTourDetail> dailyTourDetails4 = new HashSet<DailyTourDetail>();
			dailyTourDetails4.add(DailyTourDetail.builder()
					.dayOfTrip(1)
					.description("SFO Pick up")
					.build());
			dailyTourDetails4.add(DailyTourDetail.builder()
					.dayOfTrip(2)
					.description("UC Berkeley - SF City Tour")
					.build());
			dailyTourDetails4.add(DailyTourDetail.builder()
					.dayOfTrip(3)
					.description("Yosemite National Park - LA/LAX")
					.build());

			TourPackageDetail tourPackageDetail4
					= TourPackageDetail.builder()
					.departureCity("San Francisco")
					.departureDateInWeek(DayOfWeek.MONDAY)
					.description("San Francisco City Tour")
					.duration(3)
					.offerStartDate(sdf.parse("01/01/2019"))
					.offerEndDate(sdf.parse("12/31/2019"))
					.tourTitle("SF - University 17 Mile Dr")
					.build();

			dailyTourDetails4.forEach(item -> item.setTourPackageDetail(tourPackageDetail4));
			packagePricings4.forEach(item -> item.setTourPackageDetail(tourPackageDetail4));

			tourPackageDetail4.setDailyTourDetails(dailyTourDetails4);
			tourPackageDetail4.setPackagePricings(packagePricings4);

			tourPackageDetailRepository.save(tourPackageDetail4);

			TourPackageInfo tourPackageInfo4 = TourPackageInfo.builder()
					.cityOfInterest("SF - University 17 Mile Dr")
					.tourCode("FS1")
					.tourPackageDetail(tourPackageDetail4)
					.build();

			tourPackageInfoRepository.save(tourPackageInfo4);

			tourPackageDetail4.setTourPackageInfo(tourPackageInfo4);

			tourPackageDetailRepository.save(tourPackageDetail4);

			/*********************Data Point 5*****************************/

			Set<PackagePricing> packagePricings5 = new HashSet<PackagePricing>();
			packagePricings5.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.SINGLE_ROOM)
					.roomPrice(528.0)
					.build());
			packagePricings5.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.TWO_PER_ROOM)
					.roomPrice(528.0)
					.build());
			packagePricings5.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.THREE_PER_ROOM)
					.roomPrice(70.0)
					.build());
			packagePricings5.add(PackagePricing.builder()
					.roomType(RoomTypeEnum.FOUR_PER_ROOM)
					.roomPrice(308.0)
					.build());

			Set<DailyTourDetail> dailyTourDetails5 = new HashSet<DailyTourDetail>();
			dailyTourDetails5.add(DailyTourDetail.builder()
					.dayOfTrip(1)
					.description("LAX Pick up")
					.build());
			dailyTourDetails5.add(DailyTourDetail.builder()
					.dayOfTrip(2)
					.description("Pick from the top 10 options")
					.build());
			dailyTourDetails5.add(DailyTourDetail.builder()
					.dayOfTrip(3)
					.description("Pick from the top 10 options")
					.build());
			dailyTourDetails5.add(DailyTourDetail.builder()
					.dayOfTrip(4)
					.description("LAX - Las Vegas - West Grand Canyon")
					.build());
			dailyTourDetails5.add(DailyTourDetail.builder()
					.dayOfTrip(5)
					.description("West Grand Canyon - Hoover Dam - Las Vegas")
					.build());
			dailyTourDetails5.add(DailyTourDetail.builder()
					.dayOfTrip(6)
					.description("Chocolate Factory - Botanical Cactus Garden - Tanger Outlets - LA")
					.build());

			TourPackageDetail tourPackageDetail5
					= TourPackageDetail.builder()
					.departureCity("Los Angeles")
					.departureDateInWeek(DayOfWeek.THURSDAY)
					.description("Grand Canyon")
					.duration(6)
					.offerStartDate(sdf.parse("01/01/2019"))
					.offerEndDate(sdf.parse("12/31/2019"))
					.tourTitle("West Grand Canyon Cabin - Theme Park")
					.build();

			dailyTourDetails5.forEach(item -> item.setTourPackageDetail(tourPackageDetail5));
			packagePricings5.forEach(item -> item.setTourPackageDetail(tourPackageDetail5));

			tourPackageDetail5.setDailyTourDetails(dailyTourDetails5);
			tourPackageDetail5.setPackagePricings(packagePricings5);

			tourPackageDetailRepository.save(tourPackageDetail5);

			TourPackageInfo tourPackageInfo5 = TourPackageInfo.builder()
					.cityOfInterest("West Grand Canyon Cabin - Theme Park")
					.tourCode("KG3P")
					.tourPackageDetail(tourPackageDetail5)
					.build();

			tourPackageInfoRepository.save(tourPackageInfo5);

			tourPackageDetail5.setTourPackageInfo(tourPackageInfo5);

			tourPackageDetailRepository.save(tourPackageDetail5);
		}
	}
}
