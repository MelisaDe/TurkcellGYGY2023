package kodlama.io.rentacar.api.conrtollers;

import kodlama.io.rentacar.business.abstracts.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/models")
public class ModelsController {
    private final ModelService service;
}
