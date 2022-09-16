package ALP.KBECurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KbeCurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KbeCurrencyApplication.class, args);
	}

    @GetMapping("/price")
    public float getPrice(@RequestParam(value = "currency") String currency, @RequestParam(value = "value") float value) {
		switch (currency) {
			case "kyat":
			return Converter.dollarToKyat(value);
			case "dollar":
			return Converter.dollarToDollar(value);
			case "euro":
			return Converter.dollarToEuro(value);
			case "yen":
			return Converter.dollarToYen(value);
			case "riel":
			return Converter.dollarToRiel(value);
		}
		return -1.0f;
	}
}
