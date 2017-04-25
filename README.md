# systems-toolbox-ui

April 2017: this library is now DEPRECATED, it is much less cumbersome to use re-frame instead - see the updated examples in the [systems-toolbox](https://github.com/matthiasn/systems-toolbox) and [BirdWatch](https://github.com/matthiasn/BirdWatch).

This library gives you all you need for building a user interface in the browser on top of **[React](https://facebook.github.io/react/)**, using ClojureScript and the **[system-toolbox](https://github.com/matthiasn/systems-toolbox)** library.

The general idea is that there is a **systems-toolbox** component that holds
application state and business logic. This component is then observed by UI components.
The definition of the UI component is basically a function that transforms application state
into a rendered user interface. Interaction with that state component then takes place via 
sending messages. This approach is somewhat similar to the one found in **[Redux](https://github.com/reactjs/redux)**.

UI components can also hold their own, independent state and interact with it directly, 
should there be a need for this.

[![Dependencies Status](https://jarkeeper.com/matthiasn/systems-toolbox-ui/status.svg)](https://jarkeeper.com/matthiasn/systems-toolbox-ui)

## Examples

1) In the examples directory of the **[systems-toolbox](https://github.com/matthiasn/systems-toolbox/tree/master/examples)**, you can find a basic example example with some counters. This example is somewhat comparable to the respective Redux example. This example is currently client-side only.

2) A much larger example of an application using this library is **[BirdWatch](https://github.com/matthiasn/Birdwatch)**, a tweet stream analysis application that spans browser and client. There's a live version of this application **[here](https://birdwatch.matthiasnehlsen.com)**.

This library has previously been part of the **[system-toolbox](https://github.com/matthiasn/systems-toolbox)** library and was moved into a separate repository to reduce dependencies.

## Testing

As a default, the tests will run in **Chrome**. This requires you to install **[ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/)** first:
 
    $ bin/get-chromedriver.sh

With ChromeDriver installed, you can fire up the tests:

    $ lein integrations-tests


Also, you can run the tests using **[PhantomJS](http://phantomjs.org/)**:

    $ BROWSER=phantomjs lein integration-tests


Tests are run automatically on **CircleCI** using Chrome: [![CircleCI Build Status](https://circleci.com/gh/matthiasn/systems-toolbox-ui.svg?&style=shield)](https://circleci.com/gh/matthiasn/systems-toolbox-ui)

On **TravisCI**, the tests are run on **[PhantomJS](http://phantomjs.org/)**: [![TravisCI Build Status](https://travis-ci.org/matthiasn/systems-toolbox-ui.svg?branch=master)](https://travis-ci.org/matthiasn/systems-toolbox-ui)


## License

Copyright © 2015, 2016 Matthias Nehlsen

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
