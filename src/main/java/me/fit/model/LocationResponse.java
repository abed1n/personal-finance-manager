package me.fit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class LocationResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "location_seq", allocationSize = 1)
    private Long id;

    public String ip;
    public String version;
    public String city;
    public String region;
    public String region_code;
    public String country_code;
    public String country_code_iso3;
    public String country_name;
    public String country_capital;
    public String country_tld;
    public String continent_code;
    public boolean in_eu;
    public String postal;
    public double latitude;
    public double longitude;
    public String timezone;
    public String utc_offset;
    public String country_calling_code;
    public String currency;
    public String currency_name;
    public String languages;
    public double country_area;
    public int country_population;
    public String asn;
    public String org;

    @JsonBackReference("user-locations")
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public LocationResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_code_iso3() {
        return country_code_iso3;
    }

    public void setCountry_code_iso3(String country_code_iso3) {
        this.country_code_iso3 = country_code_iso3;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_capital() {
        return country_capital;
    }

    public void setCountry_capital(String country_capital) {
        this.country_capital = country_capital;
    }

    public String getCountry_tld() {
        return country_tld;
    }

    public void setCountry_tld(String country_tld) {
        this.country_tld = country_tld;
    }

    public String getContinent_code() {
        return continent_code;
    }

    public void setContinent_code(String continent_code) {
        this.continent_code = continent_code;
    }

    public boolean isIn_eu() {
        return in_eu;
    }

    public void setIn_eu(boolean in_eu) {
        this.in_eu = in_eu;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUtc_offset() {
        return utc_offset;
    }

    public void setUtc_offset(String utc_offset) {
        this.utc_offset = utc_offset;
    }

    public String getCountry_calling_code() {
        return country_calling_code;
    }

    public void setCountry_calling_code(String country_calling_code) {
        this.country_calling_code = country_calling_code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public double getCountry_area() {
        return country_area;
    }

    public void setCountry_area(double country_area) {
        this.country_area = country_area;
    }

    public int getCountry_population() {
        return country_population;
    }

    public void setCountry_population(int country_population) {
        this.country_population = country_population;
    }

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LocationResponse that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LocationResponse{" +
                "ip='" + ip + '\'' +
                ", version='" + version + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", region_code='" + region_code + '\'' +
                ", country_code='" + country_code + '\'' +
                ", country_code_iso3='" + country_code_iso3 + '\'' +
                ", country_name='" + country_name + '\'' +
                ", country_capital='" + country_capital + '\'' +
                ", country_tld='" + country_tld + '\'' +
                ", continent_code='" + continent_code + '\'' +
                ", in_eu=" + in_eu +
                ", postal='" + postal + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone='" + timezone + '\'' +
                ", utc_offset='" + utc_offset + '\'' +
                ", country_calling_code='" + country_calling_code + '\'' +
                ", currency='" + currency + '\'' +
                ", currency_name='" + currency_name + '\'' +
                ", languages='" + languages + '\'' +
                ", country_area=" + country_area +
                ", country_population=" + country_population +
                ", asn='" + asn + '\'' +
                ", org='" + org + '\'' +
                '}';
    }
}
