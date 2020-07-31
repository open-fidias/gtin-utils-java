# gtin-utils-java

Utilities for GTIN (Global Trade Item Number) barcodes.

Based on <https://github.com/tmattsson/gs1utils>.

## Installation

Add the [JitPack](https://jitpack.io/) repository to your build file:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the dependency:

```xml
<dependency>
    <groupId>com.github.open-fidias</groupId>
    <artifactId>gtin-utils-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

GTINs come in 4 lengths, GTIN-14, GTIN-13, GTIN-12 and GTIN-8, also known as UPC-A,
EAN, UCC-8, UCC-13, ITF-14. The library has methods for identification of each
length and conversion between them.

* Verification of either format or format and check digit.
* Identification of GTINs that are ISBN, ISSN, ISMN.
* Identification and value extraction from GS1 Sweden variable measure GTINs.
* Normalization of GS1 Sweden variable measure GTINs to their representative form without weight or price.
* Normalization of GTINs to their shortest representative form.

```java
// Identification and validation
GTIN.isGTIN("12345678")
GTIN.isGTIN("12345678901234")
GTIN.isGTIN14("12345678901234")
GTIN.isGTIN13("1234567890123")
GTIN.isGTIN12("123456789012")
GTIN.isGTIN8("12345678")
GTIN.isValid("12345678")  // checks both format and check digit
GTIN.validateFormat("12345678") // throws exception if format is invalid
GTIN.validateFormatAndCheckDigit("12345678") // throws exception if format or check digit invalid
```

```java
// Conversion
GTIN.toGTIN14("12345678")
GTIN.toGTIN13("12345678")
GTIN.toGTIN12("12345678")
GTIN.toGTIN8("12345678")
GTIN.convertibleToGTIN14("12345678")
GTIN.convertibleToGTIN13("12345678")
GTIN.convertibleToGTIN12("12345678")
GTIN.convertibleToGTIN8("12345678")
GTIN.shorten("000012345678") // returns "12345678" (GTIN-8)
GTIN.shorten("01234567890123") // returns "1234567890123" (GTIN-13)
GTIN.normalize("000012345678") // returns "12345678"
```

```java
// GS1 Sweden variable measure item GTINs
GTIN.isVariableMeasureItem("2388060112344")
GTIN.isVariableMeasureItemWithPrice("2088060112344")
GTIN.isVariableMeasureItemWithWeight("2388060112344")
GTIN.extractPriceFromVariableMeasureItem("02188060112344") // returns 123.40
GTIN.extractWeightFromVariableMeasureItem("02388060112344") // returns 1234
GTIN.normalizeVariableMeasureItem("2388060112344") // returns "2388060100006"
GTIN.normalize("2388060112344") // returns "2388060100006"
```

```java
// Identification of ISBN, ISSN, and ISMN numbers
GTIN.isISBN("9799137138114")
GTIN.isISSN("09772049363002")
GTIN.isISMN("9790137138114")
```
