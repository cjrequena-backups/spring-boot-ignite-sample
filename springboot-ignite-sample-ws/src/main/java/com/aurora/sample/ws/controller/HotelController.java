package com.aurora.sample.ws.controller;

import  com.aurora.sample.core.mapper.HotelMapper;
import  com.aurora.sample.core.service.HotelService;
import  com.aurora.sample.db.entity.HotelEntity;
import  com.aurora.sample.dto.HotelDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 *
 * @author
 * @version 1.0
 * @see
 * @since JDK1.8
 */
@Slf4j
@RestController
@RequestMapping(value = "/springboot-ignite-sample/")
@Api(value="Example System", description="Example system API")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * Create a new hotel resource.
     *
     * @param dto       HotelDTO
     * @param ucBuilder ucBuilder
     * @return Http status
     */
    @ApiOperation(
            tags = "HOTEL",
            value = "Create a new hotel resource",
            notes = "Returns Http Status Code 201 Created The request has been fulfilled, resulting in the creation of a new resource",
            response = Void.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 409, message = "Conflict"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(value = "/hotels/", method = RequestMethod.POST)
    public ResponseEntity<Void> createHotel(@RequestBody HotelDTO dto, UriComponentsBuilder ucBuilder) throws Exception {
        try {
            HotelEntity entity = HotelMapper.INSTANCE.toEntity(dto);
            if (hotelService.exist(entity)) {
                log.debug("A hotel with name {} and description {} already exist", entity.getName(), entity.getDescription());
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            hotelService.create(entity);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/samples/{id}").buildAndExpand(entity.getHotelId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error("Unknown error", ex);
            throw ex;
        }
    }

    /**
     * Update a hotel resource by id
     *
     * @param hotelId Integer
     * @param dto     HotelDTO
     * @return The updated dto
     */
    @ApiOperation(
            tags = "HOTEL",
            value = "Update a hotel resource by id",
            notes = "Returns Http Status Code 204 The server successfully processed the request and is not returning any content",
            response = Void.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(value = "/hotels/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateHotel(@PathVariable("id") Integer hotelId, @RequestBody HotelDTO dto) throws Exception {
        try {
            HotelEntity entity = hotelService.getById(hotelId);
            if (entity == null) {
                log.debug("Hotel entity with id {} not found", hotelId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            entity = HotelMapper.INSTANCE.toEntity(dto);
            entity.setHotelId(hotelId);
            entity.setEmail(dto.getEmail());
            hotelService.update(entity);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            log.error("Unknown error", ex);
            throw ex;
        }
    }

    /**
     * Delete a hotel resource by id
     *
     * @param hotelId Integer
     * @return Status
     */
    @ApiOperation(
            tags = "HOTEL",
            value = "Delete a hotel resource by hotelId",
            notes = "Returns Http Status Code 204 The server successfully processed the request and is not returning any content",
            response = Void.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(value = "/hotels/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HotelDTO> deleteHotel(@PathVariable("id") Integer hotelId) throws Exception {
        try {
            log.debug("Fetching & Deleting Sample with id " + hotelId);
            boolean deleted = hotelService.delete(hotelId);
            if (!deleted) {
                log.debug("Unable to delete. Hotel with id {} not found", hotelId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            log.error("Unknown error", ex);
            throw ex;
        }
    }

    /**
     * Get a hotel resource by id
     *
     * @param hotelId Integer
     * @return HotelDTO
     */
    @ApiOperation(
            tags = "HOTEL",
            value = "Get a hotel resource by id",
            notes = "Returns a Hotel resource filtered by id",
            response = HotelDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 304, message = "Not Modified"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 429, message = "Too Many Requests"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 500, message = "Service Unavailable:"),
    })
    @RequestMapping(value = "/hotels/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<HotelDTO> getHotel(@PathVariable("id") Integer hotelId) throws Exception {
        try {
            HotelEntity entity = hotelService.getById(hotelId);
            if (entity == null) {
                log.debug("Hotel entity with hotelId {} not found", hotelId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            HotelDTO dto = HotelMapper.INSTANCE.toDto(entity);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Unknown error", ex);
            throw ex;
        }
    }

    /**
     * Get a collection of hotel resources
     *
     * @return List<HotelDTO>
     */
    @ApiOperation(
            tags = "HOTEL",
            value = "Get a collection of hotel resources",
            notes = "The Hotels endpoint returns a list of hotel resources",
            response = HotelDTO.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 304, message = "Not Modified"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 429, message = "Too Many Requests"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 500, message = "Service Unavailable:"),
    })
    @RequestMapping(value = "/hotels", produces = {MediaType.APPLICATION_JSON_VALUE}, headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<List<HotelDTO>> getHotels() throws Exception {

        try {
            List<HotelEntity> entities = hotelService.getAll();
            if (entities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<HotelDTO> dtos = new ArrayList<>();

            for (HotelEntity entity : entities) {
                dtos.add(HotelMapper.INSTANCE.toDto(entity));
            }

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Unknown error", ex);
            throw ex;
        }
    }


}
