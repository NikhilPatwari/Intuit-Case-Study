//package com.example.demo.service.impl;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import com.example.demo.dto.Status;
//import com.example.demo.exception.NotFoundException;
//import com.example.demo.models.BusinessProfile;
//import com.example.demo.repository.BusinessProfileRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//class BusinessProfileManagerImplTest {
//    @Mock
//    private BusinessProfileRepository repository;
//
//    @InjectMocks
//    private BusinessProfileManagerImpl manager;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testGetBusinessProfileById_ValidId_ReturnsProfile() {
//        String validId = "123";
//        BusinessProfile expectedProfile = new BusinessProfile();
//        when(repository.findById(validId)).thenReturn(Optional.of(expectedProfile));
//        BusinessProfile result = manager.getBusinessProfileById(validId);
//        assertNotNull(result);
//        assertEquals(expectedProfile, result);
//    }
//
//    @Test
//    public void testGetBusinessProfileById_InvalidId_ThrowsNotFoundException() {
//        String invalidId = "456";
//        when(repository.findById(invalidId)).thenReturn(Optional.empty());
//        assertThrows(NotFoundException.class, () -> manager.getBusinessProfileById(invalidId));
//    }
//
//    @Test
//    public void testSaveProfile_ValidProfile_ReturnsSuccessStatus() {
//        // Arrange
//        BusinessProfile validProfile = new BusinessProfile();
//        // Act
//        Status result = manager.saveProfile(validProfile);
//        // Assert
//        assertNotNull(result);
//        assertEquals(Status.getSuccessStatus(), result);
//        // Verify that repository.save() was called
//        verify(repository, times(1)).save(validProfile);
//    }
//
////    @Test
////    public void testDeleteProfile_ValidId_ReturnsSuccessStatus() {
////        // Arrange
////        String validId = "789";
////        // Act
////        Status result = manager.deleteProfile(validId);
////        // Assert
////        assertNotNull(result);
////        assertEquals(Status.getSuccessStatus().getStatus(), result.getStatus());
////        // Verify that repository.deleteById() was called
////        verify(repository, times(1)).deleteById(validId);
////    }
//}