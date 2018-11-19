package com.philco;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

                    // AllURLs are URIs

public class Main {

    public static void main(String[] args) {

        try {
            // URI instance that will form a database query
//            URI uri = new URI("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
            // Because the URI has to be an abolute path to be converted to a url, we are going to be using http instead.
//            URI uri = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");

            // Base URI - You only have to change this once (if it's been used a lot in your code).
            URI baseUri = new URI("http://username:password@myserver.com:5000");
            // Relative URI - This specifies the path of file
//            URI uri = new URI("/catalogue/phones?os=android#samsung");

                        // Adding more relative URIs
            URI uri1 = new URI("/catalogue/phones?os=android#samsung");
            URI uri2 = new URI("/catalogue/tvs?manufacturer=samsung");
            URI uri3 = new URI("/stores/locations?zip=12345");

            // Combines both URIs
            URI resolvedURI1 = baseUri.resolve(uri1);
            URI resolvedURI2 = baseUri.resolve(uri2);
            URI resolvedURI3 = baseUri.resolve(uri3);

            // Converting a URI to a URL
            URL url1 = resolvedURI1.toURL();
            System.out.println(url1);
            URL url2 = resolvedURI2.toURL();
            System.out.println(url2);
            URL url3 = resolvedURI3.toURL();
            System.out.println(url3);

//            System.out.println ("Scheme = " + uri.getScheme ());
//            System.out.println ("Scheme-specific part = " + uri.getSchemeSpecificPart());
//            System.out.println ("Authority = " + uri.getAuthority());
//            System.out.println ("User Info = " + uri.getUserInfo());
//            System.out.println ("Host = " + uri.getHost());
//            System.out.println ("Port = " + uri.getPort());
//            System.out.println ("Path = " + uri.getPath());
//            System.out.println ("Query = " + uri.getQuery());
//            System.out.println ("Fragment = " + uri.getFragment());

        }

        catch (URISyntaxException e){
            System.out.println("URI had syntax: " + e.getMessage());
        }

        catch (MalformedURLException e){
            System.out.println("URL had syntax: " + e.getMessage());
        }
    }
}
