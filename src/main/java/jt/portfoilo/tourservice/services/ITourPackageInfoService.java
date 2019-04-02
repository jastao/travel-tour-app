package jt.portfoilo.tourservice.services;

import jt.portfoilo.tourservice.domain.TourPackageInfo;

import java.util.UUID;

public interface ITourPackageInfoService {

    TourPackageInfo findById(UUID id);
}
