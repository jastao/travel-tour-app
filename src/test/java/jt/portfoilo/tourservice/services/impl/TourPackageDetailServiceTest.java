package jt.portfoilo.tourservice.services.impl;

import jt.portfoilo.tourservice.domain.TourPackageDetail;
import jt.portfoilo.tourservice.domain.TourPackageInfo;
import jt.portfoilo.tourservice.repositories.TourPackageDetailRepository;
import jt.portfoilo.tourservice.repositories.TourPackageInfoRepository;
import jt.portfoilo.tourservice.web.rest.exceptions.TourPackageNotFoundException;
import jt.portfoilo.tourservice.web.rest.mapper.TourPackageDetailMapper;
import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailDTO;
import jt.portfoilo.tourservice.web.rest.model.TourPackageDetailPagedList;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TourPackageDetailServiceTest {

    private static final UUID testId = UUID.randomUUID();
    private static final String testTourCode = "test1";
    private static final String testDepCity = "Los Angeles";
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_TOTAL_PAGE = 1;
    
    @Mock
    private TourPackageDetailRepository tourPackageDetailRepository;

    @Mock
    private TourPackageInfoRepository tourPackageInfoRepository;

    @Mock
    private TourPackageDetailMapper tourPackageDetailMapper;

    @InjectMocks
    private TourPackageDetailService tourPackageDetailService;
    
    @Test(expected = TourPackageNotFoundException.class)
    public void givenNoTourPackageDetailId_whenFindDtoById_thenThrowTourPackageNotFoundException() {

        //given
        //when
        given(tourPackageDetailService.findDtoById(any(UUID.class))).willThrow(TourPackageNotFoundException.class);
    }
    
    @Test
    public void  givenTourPackageDetailId_whenFindDtoById_thenReturnTourPackageDetail( ) {

        //given
        TourPackageDetail testObj = TourPackageDetailTestUtil.create(testId);
        TourPackageDetailDTO testObjDto = TourPackageDetailTestUtil.createDTO(testId);

        given(tourPackageDetailRepository.findById(any(UUID.class))).willReturn(Optional.of(testObj));
        given(tourPackageDetailMapper.tourPackageDetailToDto(any(TourPackageDetail.class))).willReturn(testObjDto);

        //when
        TourPackageDetailDTO returnObj = tourPackageDetailService.findDtoById(testId);

        //then
        assertEquals(returnObj.getId(), testObjDto.getId());
        verify(tourPackageDetailRepository, times(1)).findById(testId);
        verifyNoMoreInteractions((tourPackageDetailRepository));
    }
    
    @Test(expected = TourPackageNotFoundException.class)
    public void givenNoTourPackageId_whenFindById_thenThrowTourPackageNotFoundException() {
    
        //given
        given(tourPackageDetailService.findById(any(UUID.class))).willThrow(TourPackageNotFoundException.class);
        
        //when
        tourPackageDetailService.findById(testId);
        
        verify(tourPackageDetailService, times(1)).findById(testId);
        verifyNoMoreInteractions();
    }

    @Test
    public void givenTourPackageDetailId_whenFindById_thenReturnTourPackageDetail() {
    
        //given
        Optional<TourPackageDetail> expected = Optional.of(TourPackageDetailTestUtil.create(testId));
        given(tourPackageDetailRepository.findById(any(UUID.class))).willReturn(expected);
        
        //when
        TourPackageDetail actual = tourPackageDetailService.findById(testId);
        
        //then
        assertEquals(expected.get(), actual);
        verify(tourPackageDetailRepository, times(1)).findById(testId);
        verifyNoMoreInteractions(tourPackageDetailRepository);
    }

    @Test
    public void givenTourPackageDetailDto_whenSave_returnSavedTourPackageDetailDto() {

        //given
        TourPackageDetailDTO testDtoObj = TourPackageDetailTestUtil.createDTO(testId);
        TourPackageDetail testObj = TourPackageDetail.builder().id(testDtoObj.getId()).build();
        given(tourPackageDetailMapper.dtoToTourPackageDetail(any(TourPackageDetailDTO.class))).willReturn(testObj);
        given(tourPackageDetailRepository.save(any(TourPackageDetail.class))).willReturn(testObj);
        given(tourPackageDetailMapper.tourPackageDetailToDto(any(TourPackageDetail.class))).willReturn(testDtoObj);

        //when
        TourPackageDetailDTO savedDto = tourPackageDetailService.save(testDtoObj);

        //then
        verify(tourPackageDetailMapper, times(1)).dtoToTourPackageDetail(testDtoObj);
        verify(tourPackageDetailRepository, times(1)).save(testObj);
        verify(tourPackageDetailMapper, times(1)).tourPackageDetailToDto(testObj);
    
        assertEquals(testDtoObj, savedDto);
    }

    @Test
    public void givenTourPackDetailId_thenDelete() {
        
        //given
        TourPackageDetail deleted = TourPackageDetailTestUtil.create(testId);
        given(tourPackageDetailRepository.findById(any(UUID.class))).willReturn(Optional.of(deleted));
        
        //when
        TourPackageDetailDTO returned = tourPackageDetailService.delete(testId);
        
        //then
        verify(tourPackageDetailRepository, times(1)).findById(testId);
        verify(tourPackageDetailRepository, times(1)).delete(deleted);
        verifyNoMoreInteractions(tourPackageDetailRepository);
        
        assertEquals(returned, tourPackageDetailMapper.tourPackageDetailToDto(deleted));
    }
    
    @Test(expected = TourPackageNotFoundException.class)
    public void givenTourPackDetailId_thenTourPackageNotFound() {
    
        //given
        given(tourPackageDetailRepository.findById(any(UUID.class))).willThrow(TourPackageNotFoundException.class);
        
        //when
        tourPackageDetailService.delete(testId);
        
        //then
        verify(tourPackageDetailRepository, times(1)).findById(testId);
        verifyNoMoreInteractions();
        
    }

    @Test
    public void givenTourCode_whenTourPackageByTourCode_returnTourPackageDetailDto() {
        
        //given
        TourPackageDetail testTourPackageDetail = TourPackageDetailTestUtil.create(testId);
        TourPackageDetailDTO testTourPackageDetailDto = TourPackageDetailTestUtil.createDTO(testId);
        TourPackageInfo testTourPackageInfo = TourPackageInfo.builder().cityOfInterest("Los Angeles").tourCode(testTourCode).build();
        given(tourPackageInfoRepository.findByTourCode(any(String.class))).willReturn(Optional.of(testTourPackageInfo));
        given(tourPackageDetailRepository.findByTourPackageInfo(any(TourPackageInfo.class))).willReturn(testTourPackageDetail);
        given(tourPackageDetailMapper.tourPackageDetailToDto(any(TourPackageDetail.class))).willReturn(testTourPackageDetailDto);
        
        //when
        TourPackageDetailDTO returnedDto = tourPackageDetailService.tourPackageByTourCode(testTourCode);
        
        //then
        verify(tourPackageInfoRepository, times(1)).findByTourCode(testTourCode);
        verify(tourPackageDetailRepository, times(1)).findByTourPackageInfo(testTourPackageInfo);
        assertEquals(testTourPackageDetailDto, returnedDto);
    }

    @Test(expected = TourPackageNotFoundException.class)
    public void givenTourCode_whenTourPackageByTourCode_thenThrowTourPackageNotFound() {
    
        //given
        given(tourPackageInfoRepository.findByTourCode(any(String.class))).willThrow(TourPackageNotFoundException.class);
    
        //when
        tourPackageInfoRepository.findByTourCode(testTourCode);
    
        //then
        verify(tourPackageInfoRepository, times(1)).findByTourCode(testTourCode);
        verifyNoMoreInteractions();
    
    }
    
    @Test
    @DisplayName("Find all tour packages in a paged list by departure city.")
    public void givenDepartureCity_whenTourPackages_thenReturnTourPackagePagedList() {

        //given
        List<TourPackageDetail> listOfTourPackageDetail = new ArrayList<TourPackageDetail>();
        Page<TourPackageDetail> tourPackageDetailPage = new PageImpl(listOfTourPackageDetail, PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE), DEFAULT_TOTAL_PAGE);
        TourPackageDetailPagedList expected = new TourPackageDetailPagedList(
                tourPackageDetailPage.getContent()
                                    .stream()
                                    .map(tourPackageDetailMapper::tourPackageDetailToDto)
                                    .collect(Collectors.toList()),
                PageRequest.of(tourPackageDetailPage.getNumber(), tourPackageDetailPage.getSize()),
                tourPackageDetailPage.getTotalPages()
        );
        
        given(tourPackageDetailRepository.findByDepartureCity(any(String.class), any(Pageable.class))).willReturn(tourPackageDetailPage);

        Pageable request = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        
        //when
        TourPackageDetailPagedList actual = tourPackageDetailService.tourPackages(testDepCity, request);
        
        //then
        assertEquals(DEFAULT_PAGE_NUMBER, actual.getNumber());
        assertEquals(DEFAULT_PAGE_SIZE, actual.getSize());
        
        assertEquals(expected, actual);
    }
    
    @Test
    @DisplayName("Find all tour packages in a paged list.")
    public void whenTourPackages_thenReturnTourPackagePagedList() {
    
        //given
        List<TourPackageDetail> listOfTourPackageDetail = new ArrayList<TourPackageDetail>();
        Page<TourPackageDetail> tourPackageDetailPage = new PageImpl(listOfTourPackageDetail, PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE), DEFAULT_TOTAL_PAGE);
        TourPackageDetailPagedList expected = new TourPackageDetailPagedList(
                tourPackageDetailPage.getContent()
                        .stream()
                        .map(tourPackageDetailMapper::tourPackageDetailToDto)
                        .collect(Collectors.toList()),
                PageRequest.of(tourPackageDetailPage.getNumber(), tourPackageDetailPage.getSize()),
                tourPackageDetailPage.getTotalPages()
        );
    
        given(tourPackageDetailRepository.findAll(any(Pageable.class))).willReturn(tourPackageDetailPage);
    
        Pageable request = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    
        //when
        TourPackageDetailPagedList actual = tourPackageDetailService.tourPackages(null, request);
    
        //then
        assertEquals(DEFAULT_PAGE_NUMBER, actual.getNumber());
        assertEquals(DEFAULT_PAGE_SIZE, actual.getSize());
    
        assertEquals(expected, actual);
        
    }
}