(ns wonderblog.style.standard.content
  (:require [wonderblog.style.standard.constants :as constants])
  )

(def style*
  '[".content-container"
    {
      "background-color" "rgba(254, 254, 254, 0.8)"
      "width" "100%"
      "height" "70vh" 
      "box-sizing" "border-box"
      "border-radius" constants/border-radius
      "position" "relative"
      "margin-top" constants/standard-gap
      "padding-left" "40px"
      "padding-top" "40px"
      "overflow-y" "auto"
      "color" constants/main-font-color
    }
    [".content-header"
      {
        "font-size" constants/large-font-size
        "margin-bottom" "20px"
      }
      ["hr" ; http://stackoverflow.com/questions/6382023/changing-the-color-of-a-hr-element
        {
          "background-color" constants/main-font-color
          "color" constants/main-font-color
          "border" "none"
          "height" "2px"
          "width" "90%"
          "float" "left"
          "display" "block"
          "margin" 0
        }
      ]
      ["span"
        {
          ; "width" "10vw"
          "display" "block"
        }
      ]
    ]
    [".content-title"
      {
        ; "font-size" "2vw"
        ; "font-size" "5vh"
        "font-size" constants/medium-font-size
      }
    ]
    [".content-text"
      {
        "font-size" constants/small-font-size
      }
      ["p"
        {
          "white-space" "pre-line"
        }
      ]
    ]
    [".content-links"
      {
        ; "font-size" constants/medium-font-size
        "font-size" constants/small-font-size
        "margin-left" "100px"
        "margin-bottom" "20px"
        "display" "inline-block"
      }
      ["a"
        {
          "text-decoration" "none"
          "margin-right" "20px"
        }
        ["div"
          {
            "float" "left"
            "margin-right" "20px"
          }
          ["img"
            {
              "width" "auto"
              "height" "40px"
            }
          ]
        ]
      ]
      ["a.book-link div img"
        {
          "background-color" "blue"
          "height" "200px"
          "width" "auto"
        }
      ]
    ]
    [".talk-links"
      {
        ; "display" "block"
      }
      ["a"
        {
          "display" "block"
        }

      ]
    ]
    [".tech-links"
      {
        ; "display" "block"
      }
      ["a"
        {
          "display" "block"
        }
        ["div" ; http://stackoverflow.com/questions/8865458/how-to-vertically-center-text-with-css
          {
            "width" "400px"
            "height" "70px"
            "line-height" "70px"
            ; "background-color" "blue"
            ; "border" "2px solid blue"
            "position" "relative"
          }
          ["span"
            {
              ; "vertical-align" "middle"
              ; "margin-left" "20px"
              ; "position" "relative"
              ; "background-color" "red"
              "display" "inline-block"
            }
          ]
          [".img-container"
            {
              "width" "70px"
              "height" "70px"
              "vertical-align" "middle"
              ; "background-color" "green"
              ; "background-size" "contain"
              "background-size" "40px auto"
              "background-position" "center center"
              "background-repeat" "no-repeat"
              "display" "inline-block"
            }
          ]
        ]
      ]
    ]
  ])

(def style (eval style*))
