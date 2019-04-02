package jt.portfoilo.tourservice.services.impl;

import jt.portfoilo.tourservice.domain.TourPackageInfo;
import jt.portfoilo.tourservice.repositories.TourPackageInfoRepository;
import jt.portfoilo.tourservice.services.ITourPackageInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TourPackageInfoService implements ITourPackageInfoService {

    private Logger logger = LoggerFactory.getLogger(TourPackageInfoService.class);

    private final TourPackageInfoRepository tourPackageInfoRepository;

    public TourPackageInfoService(TourPackageInfoRepository tourPackageInfoRepository) {
        this.tourPackageInfoRepository = tourPackageInfoRepository;
    }

    @Override
    public TourPackageInfo findById(UUID id) {
        Optional<TourPackageInfo> tourPackageInfoOptional =  tourPackageInfoRepository.findById(id);
        if( !tourPackageInfoOptional.isPresent() ) {
            return new TourPackageInfo();
        }
        return tourPackageInfoOptional.get();
    }
}
