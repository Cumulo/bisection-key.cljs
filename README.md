
bisection-key
----

> Generate a third string key that is in the middle when `compare`d. For example `"a"` and `"b"` result in `"aT"`.

### Usage

```edn
[cirru/bisection-key "0.1.1"]
```

[![Clojars Project](https://img.shields.io/clojars/v/cirru/bisection-key.svg)](https://clojars.org/cirru/bisection-key)

```clojure
(bisection-key.core/bisect "a" "c") ; "b"
(bisection-key.core/bisect "a" "b") ; "aT"
; make sure "a < b"

bisection-key.core/min-id ; "+"
bisection-key.core/mid-id ; "T"
bisection-key.core/max-id ; "z"
```

Charset:

```text
+-/0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
```

### Workflow

Workflow https://github.com/mvc-works/stack-workflow

### License

MIT
