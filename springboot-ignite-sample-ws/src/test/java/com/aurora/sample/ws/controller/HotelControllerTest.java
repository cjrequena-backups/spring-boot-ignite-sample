package com.aurora.sample.ws.controller;

import com.aurora.sample.core.service.HotelService;
import com.aurora.sample.db.entity.HotelEntity;
import com.aurora.sample.dto.HotelDTO;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(JMockit.class)
public class HotelControllerTest {

    @Tested
    HotelController hotelController;

    @Injectable
    private HotelService hotelService;

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }


    /**
     * @throws Exception
     */
    @Test
    public void getHotelMustReturnSuccessStatusCode200() throws Exception {
        try {

            HotelEntity entity = new HotelEntity();
            entity.setHotelId(1);
            entity.setName("Hotel costa azul");
            entity.setDescription("Default Description");
            entity.setEmail("info@hotelcostaazul.com");
            entity.setFax("555 555 555");
            entity.setLocalCurrencyCode("'EUR'");
            entity.setPhone("555 555 555");
            entity.setRate("5-start");

            // Define the Expectations block here
            Expectations expectations = new Expectations() {
                // variables declared here are mocked by default
                {
                    hotelService.getById(1);
                    returns(entity);
                }
            };

            final ResponseEntity<HotelDTO> responseEntity = hotelController.getHotel(1);

            // Check that an object isn't null
            assertNotNull("The responseEntity is not as expected", responseEntity);

            // Check that an object isn't null
            assertNotNull("The body content is not as expected", responseEntity.getBody());

            // Check some content
            assertEquals("The status code is not as expected", responseEntity.getStatusCode(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void getHotelMustReturnNotModifiedStatusCode304() throws Exception {

    }

    /**
     * @throws Exception
     */
    @Test
    public void getHotelMustReturnUnauthorizedStatusCode401() throws Exception {

    }


    /**
     * @throws Exception
     */
    @Test
    public void getHotelMustReturnNotFoundStatusCode404() throws Exception {
        try {

            // Define the Expectations block here
            Expectations expectations = new Expectations() {
                // variables declared here are mocked by default
                {
                    hotelService.getById(1);
                    returns(null);
                }
            };

            final ResponseEntity<HotelDTO> responseEntity = hotelController.getHotel(1);

            // Check that an object isn't null
            assertNotNull("The responseEntity is not as expected", responseEntity);

            // Check that an object isn't null
            assertNull("The body content is not as expected", responseEntity.getBody());

            // Check some content
            assertEquals("The status code is not as expected", responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void getHotelMustReturnTooManyRequestsStatusCode429() throws Exception {

    }

    /**
     * @throws Exception
     */
    @Test
    public void getHotelMustReturnInternalServerErrorStatusCode500() throws Exception {

    }


    /**
     * @throws Exception
     */
    @Test
    public void getHotelsMustReturnSuccessStatusCode200() throws Exception {
        try {

            List<HotelEntity> entities = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                HotelEntity entity = new HotelEntity();
                entity.setHotelId(i);
                entity.setName("Hotel_" + i);
                entity.setDescription("HotelDesc_" + i);
                entity.setFax("HotelFax_" + i);
                entities.add(entity);
            }

            // Define the Expectations block here
            Expectations expectations = new Expectations() {
                // variables declared here are mocked by default
                {
                    hotelService.getAll();
                    returns(entities);
                }
            };

            final ResponseEntity<List<HotelDTO>> responseEntity = hotelController.getHotels();

            // Check that an object isn't null
            assertNotNull("The responseEntity is not as expected", responseEntity);

            // Check that an object isn't null
            assertNotNull("The body content is not as expected", responseEntity.getBody());

            // Check the size
            assertEquals("The content size is not as expected", responseEntity.getBody().size(), 10);

            // Check some content
            assertEquals("The status code is not as expected", responseEntity.getStatusCode(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void getHotelsMustReturnNotModifiedStatusCode304() throws Exception {

    }

    /**
     * @throws Exception
     */
    @Test
    public void getHotelsMustReturnUnauthorizedStatusCode401() throws Exception {

    }

    /**
     * @throws Exception
     */
    @Test
    public void getHotelsMustReturnNotFoudStatusCode404() throws Exception {
        try {

            List<HotelEntity> entities = new ArrayList<>();

            // Define the Expectations block here
            Expectations expectations = new Expectations() {
                // variables declared here are mocked by default
                {
                    hotelService.getAll();
                    returns(entities);
                }
            };

            final ResponseEntity<List<HotelDTO>> responseEntity = hotelController.getHotels();

            // Check that an object isn't null
            assertNotNull("The responseEntity is not as expected", responseEntity);

            // Check that an object is null
            assertNull("The body content is not as expected", responseEntity.getBody());

            // Check some content
            assertEquals("The status code is not as expected", responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void getHotelsMustReturnTooManyRequestsStatusCode429() throws Exception {

    }

    /**
     * @throws Exception
     */
    @Test
    public void getHotelsMustReturnInternalServerErrorStatusCode500() throws Exception {

    }

    /**
     * @throws Exception
     */
    @Test
    public void createHotelMustReturnCreatedStatusCode201() throws Exception {

    }

    /**
     * @throws Exception
     */
    @Test
    public void createHotelMustReturnUnauthorizedStatusCode401() throws Exception {

    }

    /**
     * @throws Exception
     */
    @Test
    public void createHotelMustReturnConflictStatusCode403() throws Exception {

    }

    /**
     * @throws Exception
     */
    @Test
    public void createHotelMustReturnFailureStatusCode409() throws Exception {

    }

}