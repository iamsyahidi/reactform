package com.iam.reactform.controller.restapi;


import com.iam.reactform.model.dto.LoginDto;
import com.iam.reactform.model.dto.UserDto;
import com.iam.reactform.model.entity.Biodata;
import com.iam.reactform.model.entity.User;
import com.iam.reactform.repository.BiodataRepository;
import com.iam.reactform.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class ApiLogin {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BiodataRepository biodataRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get/all")
    public List<UserDto> getListBiodataUser(){
        List<UserDto> userDtos = new ArrayList<>();
        List<Biodata> biodataList = biodataRepository.findAll();
        for (Biodata biodata : biodataList){
            UserDto userDto = new UserDto();
            User user = userRepository.findByBiodataIdBio(biodata.getIdBio());
            userDto.setPassword(user.getPassword());
            userDto.setUsername(user.getUsername());
            userDto.setIdUser(user.getIdUser());
            userDto.setBio(biodata.getBio());
            userDto.setBirthDate(biodata.getBirthDate());
            userDto.setEmail(biodata.getEmail());
            userDto.setFullName(biodata.getFullName());
            userDto.setIdBio(biodata.getIdBio());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @GetMapping("/get/{idBio}")
    public UserDto getBiodataUser (@PathVariable Integer idBio){
        User user = userRepository.findByBiodataIdBio(idBio);
        UserDto userDto = new UserDto();
        userDto.setIdUser(user.getIdUser());
        userDto.setIdBio(user.getBiodata().getIdBio());
        userDto.setFullName(user.getBiodata().getFullName());
        userDto.setEmail(user.getBiodata().getEmail());
        userDto.setBirthDate(user.getBiodata().getBirthDate());
        userDto.setBio(user.getBiodata().getBio());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    @PostMapping("/regis")
    public UserDto registrasiUser(@RequestBody UserDto userDto){

        Biodata biodata = new Biodata();
        biodata.setIdBio(userDto.getIdBio());
        biodata.setFullName(userDto.getFullName());
        biodata.setEmail(userDto.getEmail());
        biodata.setBirthDate(userDto.getBirthDate());
        biodata.setBio(userDto.getBio());
        biodataRepository.save(biodata);

        User user = new User();
        user.setIdUser(userDto.getIdUser());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setBiodata(biodata);
        userRepository.save(user);
        return userDto;
    }

    //Post new data
    @PostMapping
    public ResponseEntity<Boolean> user(@RequestBody LoginDto loginDto, HttpServletRequest request){
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++){
            if (loginDto.getUsername().equals(userList.get(i).getUsername()) && loginDto.getPassword().equals(userList.get(i).getPassword())){

                return ResponseEntity.ok(Boolean.TRUE);
            }
        }
        return ResponseEntity.ok(Boolean.FALSE);
    }

    @PutMapping("/edit/{idBio}")
    public ResponseEntity<Biodata> updateBiodata(@PathVariable Integer idBio, @Valid @RequestBody Biodata biodata){
        Biodata biodata1 = biodataRepository.findById(idBio).get();
        modelMapper.map(biodata, biodata1);
        final Biodata updateBiodata = biodataRepository.save(biodata1);
        return ResponseEntity.ok(updateBiodata);
    }

    @DeleteMapping("/delete/{idUser}")
    public void delete (@PathVariable Integer idUser){
        userRepository.deleteById(idUser);
    }
}
