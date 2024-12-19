package com.solvd.carina.demo.gui.components;

import com.solvd.carina.demo.gui.enums.Category;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class SelectComponent extends AbstractUIObject {

    @FindBy(css = "option")
    private List<ExtendedWebElement> options;

    public SelectComponent(WebDriver driver) {
        super(driver);
    }

    public List<ExtendedWebElement> getOptions() {
        return options;
    }

    public void clickOption(Category category) {
        Optional<ExtendedWebElement> opt = options.stream()
                .filter(option -> option.getText().equals(category.getDisplayName()))
                .findFirst();
        opt.ifPresent(ExtendedWebElement::click);
    }
}