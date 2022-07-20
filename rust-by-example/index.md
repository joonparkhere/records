---
title: Hello Rust By Example
date: 2022-07-20
pin: false
tags:
- Rust
---

## Introduction

```rust
mod mod1;

fn main() {
    mod1::mod1fn();
}
// rustc main.rs
```

## Hello World

```rust
fn intro() {
    println!("Hello world!");
    println!("I'm a Rustacean!");
}

fn format_print() {
    eprintln!("Error print");

    print!("{} Month is ", 1);
    println!("{}", format!("{} days", 31));
    // 1 Month is 31 days

    println!("{0}, this is {1}. {1}, this is {0}", "Alice", "Bob");
    // Alice, this is Bob. Bob, this is Alice

    println!("{subject} {verb} {object}",
        subject="the quick brown fox",
        verb="jumps over",
        object="the lazy dog",
    );
    // the quick brown fox jumps over the lazy dog

    println!("Base 10 repr:               {}",   69420);
    println!("Base 2 (binary) repr:       {:b}", 69420);
    println!("Base 8 (octal) repr:        {:o}", 69420);
    println!("Base 16 (hexadecimal) repr: {:x}", 69420);
    println!("Base 16 (hexadecimal) repr: {:X}", 69420);
    // Base 10 repr:               69420
    // Base 2 (binary) repr:       10000111100101100
    // Base 8 (octal) repr:        207454
    // Base 16 (hexadecimal) repr: 10f2c
    // Base 16 (hexadecimal) repr: 10F2C

    println!("{number:>5}", number=1);
    println!("{number:0>width$}", number=1, width=5);
    //     1
    // 00001

    let number: f64 = 1.0;
    let width: usize = 5;
    println!("{number:0>width$}");
    // 00001
}

#[derive(Debug)]
struct Person<'a> {
    name: &'a str,
    age: u8
}

fn debug() {
    let name = "Peter";
    let age = 27;
    let peter = Person { name, age };

    println!("{:#?}", peter);
    // Person {
    //     name: "Peter",
    //     age: 27,
    // }
}

use std::fmt::{self, Formatter, Display, write};

struct Point2D {
    x: f64,
    y: f64,
}

impl Display for Point2D {
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        write!(f, "x: {}, y: {}", self.x, self.y)
    }
}

struct MyList(Vec<i32>);

impl Display for MyList {
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        let vec = &self.0;

        write!(f, "[")?;
        for (count, v) in vec.iter().enumerate() {
            if count != 0 { write!(f, ", ")?; }
            write!(f, "{}: {}", count, v)?;
        }
        write!(f, "]")
    }
}

fn display() {
    let point = Point2D { x: 3.3, y: 2.2 };
    println!("Display: {}", point);
    // Display: x: 3.3, y: 2.2

    let v = MyList(vec![1, 2, 3]);
    println!("{}", v);
    // [0: 1, 1: 2, 2: 3]
}

struct City {
    name: &'static str,
    lat: f32,
    lon: f32,
}

impl Display for City {
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        let lat_c = if self.lat >= 0.0 { 'N' } else { 'S' };
        let lon_c = if self.lon >= 0.0 { 'E' } else { 'W' };
        write!(f, "{}: {:.3}°{} {:.3}°{}",
            self.name, self.lat.abs(), lat_c, self.lon.abs(), lon_c
        )
    }
}

struct Color {
    red: u8,
    green: u8,
    blue: u8,
}

impl Display for Color {
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        write!(f, "RGB ({0}, {1}, {2}) 0x{0:0>2X}{1:0>2X}{2:0>2X}",
               self.red, self.green, self.blue
        )
    }
}

fn formatting() {
    for city in [
        City { name: "Dublin", lat: 53.347778, lon: -6.259722 },
        City { name: "Oslo", lat: 59.95, lon: 10.75 },
        City { name: "Vancouver", lat: 49.25, lon: -123.1 },
    ].iter() {
        println!("{}", *city);
    }
    // Dublin: 53.348°N 6.260°W
    // Oslo: 59.950°N 10.750°E
    // Vancouver: 49.250°N 123.100°W

    for color in [
        Color { red: 128, green: 255, blue: 90 },
        Color { red: 0, green: 3, blue: 254 },
        Color { red: 0, green: 0, blue: 0 },
    ].iter() {
        println!("{}", *color);
    }
    // RGB (128, 255, 90) 0x80FF5A
    // RGB (0, 3, 254) 0x0003FE
    // RGB (0, 0, 0) 0x000000
}
```

## Primitives

```rust
fn types() {
    let logical: bool = true;

    let default_float = 3.0; // f64
    let default_integer = 7; // i32

    let a_float: f64 = 1.0;
    let an_integer = 5i32;
    // a_float = 1.1; // cannot be changed

    let mut inferred_type = 12; // i64
    inferred_type = 4294967296i64;

    let mut mutable = 12;
    mutable = 21;   // can be changed
    let mutable = true; // overwritten with shadowing
}

fn literals_and_operators() {
    println!("1 + 2 = {}", 1u32 + 2);
    println!("1 - 2 = {}", 1i32 - 2);
    // 1 + 2 = 3
    // 1 - 2 = -1

    // println!("1 - 2 = {}", 1u32 - 2); // overflow

    println!("true AND false is {}", true && false);
    println!("true OR false is {}", true || false);
    println!("NOT true is {}", !true);
    // true AND false is false
    // true OR false is true
    // NOT true is false

    println!("0011 AND 0101 is {:04b}", 0b0011u32 & 0b0101);
    println!("0011 OR 0101 is {:04b}", 0b0011u32 | 0b0101);
    println!("0011 XOR 0101 is {:04b}", 0b0011u32 ^ 0b0101);
    println!("1 << 5 is {}", 1u32 << 5);
    println!("0x80 >> 2 is 0x{:x}", 0x80u32 >> 2);
    // 0011 AND 0101 is 0001
    // 0011 OR 0101 is 0111
    // 0011 XOR 0101 is 0110
    // 1 << 5 is 32
    // 0x80 >> 2 is 0x20

    println!("One million is written as {}", 1_000_000u32); // to improve readability
    // One million is written as 1000000
}

fn tuples() {
    let long_tuple = (
        1u8, 2u16, 3u32, 4u64,
        -1i8, -2i16, -3i32, -4i64,
        0.1f32, 0.2f64, 'a', true
    );
    println!("long tuple first value: {}", long_tuple.0);
    println!("long tuple second value: {}", long_tuple.1);
    // long tuple first value: 1
    // long tuple second value: 2

    let tuple_of_tuples = ((1u8, 2u16, 2u32), (4u64, -1i8), -2i16);
    println!("tuple of tuples: {:?}", tuple_of_tuples);
    // tuple of tuples: ((1, 2, 2), (4, -1), -2)

    println!("one element tuple: {:?}", (5u32,));
    println!("just an integer: {:?}", (5u32));
    // one element tuple: (5,)
    // just an integer: 5

    let tuple = (1, "hello", 4.5, true);
    let (a, b, c, d) = tuple;
    println!("{:?}, {:?}, {:?}, {:?}", a, b, c, d);
    // 1, "hello", 4.5, true
}

use std::mem;

fn arrays_and_slices() {
    let xs: [i32; 5] = [1, 2, 3, 4, 5];
    println!("first element of the array: {}", xs[0]);
    println!("number of elements in array: {}", xs.len());
    println!("array occupies {} bytes", mem::size_of_val(&xs));
    // first element of the array: 1
    // number of elements in array: 5
    // array occupies 20 bytes

    let ys: [i32; 5] = [0; 5];
    for (idx, elem) in ys[1..4].iter().enumerate() {
        println!("{}: {}", idx, elem);
    }
    // 0: 0
    // 1: 0
    // 2: 0
}
```

## Custom Types

```rust
// Unit Struct, which useful for generics
struct Unit;

// Tuple Struct
struct Pair(i32, f32);

// Classic Struct
struct Point {
    x: f32,
    y: f32,
}

struct Rectangle {
    top_left: Point,
    bottom_right: Point,
}

fn structures() {
    let _unit = Unit;

    let pair = Pair(1, 0.1);
    println!("pair contains {:?} and {:?}", pair.0, pair.1);
    // pair contains 1 and 0.1

    let Pair(integer, decimal) = pair;
    println!("destructured pair {:?} and {:?}", integer, decimal);
    // destructured pair 1 and 0.1

    let point: Point = Point { x: 10.3, y: 0.4 };
    println!("point coordinates: ({}, {})", point.x, point.y);
    // point coordinates: (10.3, 0.4)

    let bottom_right_point = Point { x: 5.2, ..point };
    println!("second point: ({}, {})", bottom_right_point.x, bottom_right_point.y);
    // second point: (5.2, 0.4)

    let Point { x: left_edge, y: top_edge } = point;
    println!("destructured point: ({}, {})", left_edge, top_edge);
    // destructured point: (10.3, 0.4)

    let _rectangle = Rectangle {
        top_left: Point { x: left_edge, y: top_edge },
        bottom_right: bottom_right_point,
    };
}

enum Number {
    Zero,
    One,
    Two,
}

enum Color {
    Red = 0xff0000,
    Green = 0x00ff00,
    Blue = 0x0000ff,
}

fn enums() {
    println!("zero is {}", Number::Zero as i32);
    println!("one is {}", Number::One as i32);
    // zero is 0
    // one is 1

    println!("roses are #{:06x}", Color::Red as i32);
    println!("violets are #{:06x}", Color::Blue as i32);
    // roses are #ff0000
    // violets are #0000ff
}

enum WebEvent {
    PageLoad,
    PageUnload,
    KeyPress(char),
    Paste(String),
    Click {x: i64, y: i64},
}

fn inspect(event: WebEvent) {
    match event {
        WebEvent::PageLoad => println!("page loaded"),
        WebEvent::PageUnload => println!("page unloaded"),
        WebEvent::KeyPress(c) => println!("pressed '{}'.", c),
        WebEvent::Paste(s) => println!("pasted \"{}\".", s),
        WebEvent::Click { x, y } => println!("clicked at x={}, y={}.", x, y),
    }
}

fn web_events() {
    use WebEvent::*;

    let pressed = KeyPress('x');
    let pasted  = Paste("my text".to_owned()); // `to_owned()` creates an owned `String` from a string slice.
    let click   = Click { x: 20, y: 80 };
    let load    = PageLoad;
    let unload  = PageUnload;

    inspect(pressed);
    inspect(pasted);
    inspect(click);
    inspect(load);
    inspect(unload);
    // pressed 'x'.
    // pasted "my text".
    // clicked at x=20, y=80.
    // page loaded
    // page unloaded
}

enum VeryVerboseEnumOfThingsToDoWithNumbers {
    Add,
    Subtract,
}

impl VeryVerboseEnumOfThingsToDoWithNumbers {
    fn run(&self, x: i32, y: i32) -> i32 {
        match self {
            Self::Add => x + y,
            Self::Subtract => x - y,
        }
    }
}

type Operations = VeryVerboseEnumOfThingsToDoWithNumbers;

fn type_aliases() {
    let _x = Operations::Add;
}

use LinkedList::*;

enum LinkedList {
    Node(u32, Box<LinkedList>),
    Nil,
}

use std::fmt::format;

impl LinkedList {
    fn new() -> LinkedList {
        Nil
    }

    fn prepend(self, elem: u32) -> LinkedList {
        Node(elem, Box::new(self))
    }

    fn len(&self) -> u32 {
        match *self {
            Node(_, ref tail) => 1 + tail.len(),
            Nil => 0,
        }
    }

    fn stringify(&self) -> String {
        match *self {
            Node(elem, ref tail) => {
                format!("{}, {}", elem, tail.stringify())
            }
            Nil => {
                format!("Nil")
            },
        }
    }
}

fn linked_list() {
    let mut list = LinkedList::new();
    println!("dummy linked list has length: {}", list.len());
    println!("{}", list.stringify());
    // dummy linked list has length: 0
    // Nil

    list = list.prepend(1);
    list = list.prepend(2);
    list = list.prepend(3);

    println!("final linked list has length: {}", list.len());
    println!("{}", list.stringify());
    // final linked list has length: 3
    // 3, 2, 1, Nil
}

static LANGUAGE: &str = "Rust";
const THRESHOLD: i32 = 10;

fn is_big(n: i32) -> bool {
    n > THRESHOLD
}

fn constants() {
    // THRESHOLD = 5; // cannot modify a `const`

    println!("This is {}", LANGUAGE);
    println!("The threshold is {}", THRESHOLD);
    // This is Rust
    // The threshold is 10

    let n = 16;
    println!("{} is {}", n, if is_big(n) { "big" } else { "small" });
    // 16 is big
}
```

## Variable Bindings

```rust
fn intro() {
    let an_integer = 1u32;
    let a_boolean = true;
    let unit = ();

    let copied_integer = an_integer;

    println!("An integer: {:?}", copied_integer);
    println!("A boolean: {:?}", a_boolean);
    println!("Meet the unit value: {:?}", unit);
    // An integer: 1
    // A boolean: true
    // Meet the unit value: ()
}

fn mutability() {
    let immutable_binding = 1;
    // immutable_binding += 1; // compiler throw error because immutable by default

    let mut mutable_binding = 1;
    println!("Before mutation: {}", mutable_binding);
    mutable_binding += 1;
    println!("After mutation: {}", mutable_binding);
    // Before mutation: 1
    // After mutation: 2
}

fn scope() {
    let long_lived_binding = 1;

    {
        let short_lived_binding = 2;
        println!("inner short: {}", short_lived_binding);
        // inner short: 2
    }

    // println!("outer short: {}", short_lived_binding); // error! not exist in this scope

    println!("outer long: {}", long_lived_binding);
    // outer long: 1
}

fn shadowing() {
    let shadowed_binding = 1;

    {
        println!("before being shadowed: {}", shadowed_binding);
        // before being shadowed: 1

        let shadowed_binding = "abc";
        println!("shadowed in inner block: {}", shadowed_binding);
        // shadowed in inner block: abc
    }

    println!("outside after block: {}", shadowed_binding);
    // outside after block: 1

    let shadowed_binding = 2;
    println!("shadowed after block: {}", shadowed_binding);
    // shadowed after block: 2
}

fn declare_first() {
    let a_binding;
    // println!("before a binding: {}", a_binding); // error! uninitialized variable

    {
        let x = 2;
        a_binding = x * x;
    }

    println!("a binding: {}", a_binding);
    // a binding: 4

    let another_binding;
    another_binding = 1;
    println!("another binding: {}", another_binding);
    // another binding: 1
}

fn freezing() {
    let mut _mutable_integer = 7i32;

    {
        let _mutable_integer = _mutable_integer;
        // _mutable_integer = 50; // error! frozen in this scope
    }

    _mutable_integer = 3;
}
```

## Types

```rust
#![allow(overflowing_literals)]

fn casting() {
    let decimal = 65.4321_f32;

    // let integer: u8 = decimal; // error! no implicit conversion
    let integer = decimal as u8;
    let character = integer as char;
    // let character = decimal as char; // error! cannot be directly converted

    println!("Casting: {} -> {} -> {}", decimal, integer, character);
    // Casting: 65.4321 -> 65 -> A

    /*
    when casting any value to an unsigned type, T,
    T::MAX + 1 is added or subtracted until the value
    fits into the new type
     */

    println!("1000 as a u8 is : {}", 1000 as u8); // 1000 - 256 - 256 - 256 = 232
    // 1000 as a u8 is : 232

    println!("  -1 as a u8 is : {}", (-1i8) as u8); // -1 + 256 = 255
    //   -1 as a u8 is : 255


    /*
    When casting to a signed type, the (bitwise) result is the same as
    first casting to the corresponding unsigned type. If the most significant
    bit of that value is 1, then the value is negative.
     */

    println!(" 128 as a i8 is : {}", 128 as i8);
    //  128 as a i8 is : -128

    /*
    Since Rust 1.45, the `as` keyword performs a *saturating cast*
    when casting from float to int. If the floating point value exceeds
    the upper bound or is less than the lower bound, the returned value
    will be equal to the bound crossed.
     */

    println!("300.0 is {}", 300.0_f32 as u8);
    println!("-100.0 as u8 is {}", -100.0_f32 as u8);
    println!("nan as u8 is {}", f32::NAN as u8);
    // 300.0 is 255
    // -100.0 as u8 is 0
    // nan as u8 is 0

    /*
    This behavior incurs a small runtime cost and can be avoided
    with unsafe methods, however the results might overflow and
    return **unsound values**. Use these methods wisely:
     */
    unsafe {
        println!("300.0 is {}", 300.0_f32.to_int_unchecked::<u8>());
        println!("-100.0 as u8 is {}", (-100.0_f32).to_int_unchecked::<u8>());
        println!("nan as u8 is {}", f32::NAN.to_int_unchecked::<u8>());
        // 300.0 is 44
        // -100.0 as u8 is 0
        // nan as u8 is 0
    }
}

fn literals() {
    let x = 1u8;
    let y = 2u32;
    let z = 3f32;
    // Suffixed literals, their types are known at initialization

    let i = 1;
    let f = 1.0;
    // Unsuffixed literals, their types depend on how they are used

    println!("size of `x` in bytes: {}", std::mem::size_of_val(&x));
    println!("size of `y` in bytes: {}", std::mem::size_of_val(&y));
    println!("size of `z` in bytes: {}", std::mem::size_of_val(&z));
    println!("size of `i` in bytes: {}", std::mem::size_of_val(&i));
    println!("size of `f` in bytes: {}", std::mem::size_of_val(&f));
    // size of `x` in bytes: 1
    // size of `y` in bytes: 4
    // size of `z` in bytes: 4
    // size of `i` in bytes: 4
    // size of `f` in bytes: 8
}

fn inference() {
    let elem = 5u8;

    let mut vec = Vec::new();
    // At this point the compiler doesn't know the exact type of `vec`, it
    // just knows that it's a vector of something (`Vec<_>`).

    vec.push(elem);
    // Aha! Now the compiler knows that `vec` is a vector of `u8`s (`Vec<u8>`)

    println!("{:?}", vec);
    // [5]
}

type NanoSecond = u64;
type Inch = u64;
type U64 = u64;

fn aliasing() {
    let nanoseconds: NanoSecond = 5 as U64;
    let inches: Inch = 2 as U64;

    println!(
        "{} nanoseconds + {} inches = {} unit?",
        nanoseconds,
        inches,
        nanoseconds + inches
    );
    // 5 nanoseconds + 2 inches = 7 unit?
}
```

## Conversion

```rust
use std::convert::From;

#[derive(Debug)]
struct MyNumber {
    value: i32,
}

impl From<i32> for MyNumber {
    fn from(item: i32) -> Self {
        MyNumber { value: item }
    }
}

fn from_and_into() {
    let num_from = MyNumber::from(30);
    println!("num_from is {:?}", num_from);
    // num_from is MyNumber { value: 30 }

    let int = 5;
    let num_to: MyNumber = int.into();
    println!("num_to is {:?}", num_to);
    // num_to is MyNumber { value: 5 }
}

use std::convert::TryFrom;
use std::convert::TryInto;

#[derive(Debug)]
struct EvenNumber(i32);

impl TryFrom<i32> for EvenNumber {
    type Error = ();
    
    fn try_from(value: i32) -> Result<Self, Self::Error> {
        if value % 2 == 0 {
            Ok(EvenNumber(value))
        } else {
            Err(())
        }
    }
}

impl PartialEq for EvenNumber {
    fn eq(&self, other: &Self) -> bool {
        self.0 == other.0
    }
}

fn try_from_and_try_into() {
    assert_eq!(
        EvenNumber::try_from(8),
        Ok(EvenNumber(8))
    );
    assert_eq!(
        EvenNumber::try_from(5),
        Err(())
    );

    let result: Result<EvenNumber, ()> = 8i32.try_into();
    assert_eq!(
        result,
        Ok(EvenNumber(8))
    );
    let result: Result<EvenNumber, ()> = 5i32.try_into();
    assert_eq!(
        result,
        Err(())
    );
}

use std::fmt;
use std::fmt::Formatter;

struct Circle {
    radius: i32,
}

impl fmt::Display for Circle {
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        write!(f, "Circle of radius {}", self.radius)
    }
}

fn to_and_from_strings() {
    let circle = Circle { radius: 6 };
    println!("{}", circle.to_string());
    // Circle of radius 6

    let parsed: i32 = "5".parse().unwrap();
    let turbo_parsed = "10".parse::<i32>().unwrap();
    let sum = parsed + turbo_parsed;
    println!("Sum: {:?}", sum);
    // Sum: 15
}
```

## Expressions

```rust
fn expressions() {
    let x = 5u32;

    let y = {
        let x_squared = x * x;
        let x_cube = x_squared * x;

        x_cube + x_squared + x
        // This no semicolon expression will be assigned to `y`
    };

    let z = {
        2 * x;
        // The semicolon suppresses this expression and `()` is assigned to `z`
    };

    println!("x is {:?}", x);
    println!("y is {:?}", y);
    println!("z is {:?}", z);
    // x is 5
    // y is 155
    // z is ()
}
```

## Flow of Control

## Functions

## Modules

## Crates

## Cargo

## Attributes

## Generics

## Scoping Rules

## Traits

## `macro_rules!`

## Error Handling

## Std Library Types

## Std mics

## Testing

## Unsafe Operations

## Compatibility

## Meta
