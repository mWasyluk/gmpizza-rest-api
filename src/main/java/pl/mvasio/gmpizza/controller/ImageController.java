package pl.mvasio.gmpizza.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/images", produces = MediaType.IMAGE_JPEG_VALUE)
public class ImageController {

    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable(value = "name" ) String name) throws IOException {
        InputStream in = getClass().getResourceAsStream("/images/" + name);

        if (in != null){
            return new ResponseEntity<>(IOUtils.readAllBytes(in), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
