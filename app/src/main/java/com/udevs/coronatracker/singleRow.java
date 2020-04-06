package com.udevs.coronatracker;

public class singleRow {
    String country;
    String cases;
    String imageLink;
    String today_Cases;
    String recovered;
    String today_deaths;
    String deaths;

    public singleRow(String country, String cases, String imageLink, String today_Cases, String recovered, String today_deaths, String deaths) {
        this.country = country;
        this.cases = cases;
        this.imageLink = imageLink;
        this.today_Cases = today_Cases;
        this.recovered = recovered;
        this.today_deaths = today_deaths;
        this.deaths = deaths;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getToday_Cases() {
        return today_Cases;
    }

    public void setToday_Cases(String today_Cases) {
        this.today_Cases = today_Cases;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getToday_deaths() {
        return today_deaths;
    }

    public void setToday_deaths(String today_deaths) {
        this.today_deaths = today_deaths;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }
}
