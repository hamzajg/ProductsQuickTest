package com.hamzajg.quickstart.product.webapi.endpoints;

import com.hamzajg.quickstart.product.webapi.GreetingResourceTest;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeGreetingResourceIT extends GreetingResourceTest {

    // Execute the same tests but in native mode.
}