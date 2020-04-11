
  import React, { useRef, useEffect, useState } from "react";
  import { select, hierarchy, tree, linkHorizontal } from "d3";


  
  function usePrevious(value) {
    const ref = useRef();
    useEffect(() => {
      ref.current = value;
    });
    return ref.current;
  }
  
  function TreeChart() {
    const svgRef = useRef();
    const wrapperRef = useRef();
    const useResizeObserver = ref => {
        const [dimensions, setDimensions] = useState(null);
        useEffect(() => {
          const observeTarget = ref.current;
          const resizeObserver = new ResizeObserver(entries => {
            entries.forEach(entry => {
              setDimensions(entry.contentRect);
            });
          });
          resizeObserver.observe(observeTarget);
          return () => {
            resizeObserver.unobserve(observeTarget);
          };
        }, [ref]);
        return dimensions;
      };
    const dimensions = useResizeObserver(wrapperRef);

    const initialData = {
        name: "William",
        type: "Alpha",
        planet: "Earth",
        children: [
          {
            name: "Henry",
            type: "Alpha",
            planet: "Earth",
            children: [
              {
                name: "Matt",
                type: "Beta",
                planet: "Mars",
                children:[]
              },
              {
                name: "Alisa",
                type: "Beta",
                planet: "Mars",
                children:[]
              }
            ]
          }
        ]
      };
    
    // we save data to see if it changed
    const previouslyRenderedData = usePrevious(initialData);
  
    // will be called initially and on every data change
    useEffect(() => {
      const svg = select(svgRef.current);
  
      // use dimensions from useResizeObserver,
      // but use getBoundingClientRect on initial render
      // (dimensions are null for the first render)
      const { width, height } =
        dimensions || wrapperRef.current.getBoundingClientRect();
  
      // transform hierarchical data
      const root = hierarchy(initialData);
      const treeLayout = tree().size([height, width]);
  
      const linkGenerator = linkHorizontal()
        .x(link => link.y)
        .y(link => link.x);
  
      // enrich hierarchical data with coordinates
      treeLayout(root);
  
      console.warn("descendants", root.descendants());
      console.warn("links", root.links());
  
      // nodes
      svg
        .selectAll(".node")
        .data(root.descendants())
        .join(enter => enter.append("circle").attr("opacity", 0))
        .attr("class", "node")
        .attr("cx", node => node.y)
        .attr("cy", node => node.x)
        .attr("r", 4)
        .transition()
        .duration(500)
        .delay(node => node.depth * 300)
        .attr("opacity", 1);
  
      // links
      const enteringAndUpdatingLinks = svg
        .selectAll(".link")
        .data(root.links())
        .join("path")
        .attr("class", "link")
        .attr("d", linkGenerator)
        .attr("stroke-dasharray", function() {
          const length = this.getTotalLength();
          return `${length} ${length}`;
        })
        .attr("stroke", "black")
        .attr("fill", "none")
        .attr("opacity", 1);
  
      if (initialData !== previouslyRenderedData) {
        enteringAndUpdatingLinks
          .attr("stroke-dashoffset", function() {
            return this.getTotalLength();
          })
          .transition()
          .duration(500)
          .delay(link => link.source.depth * 500)
          .attr("stroke-dashoffset", 0);
      }
  
      // labels
      svg
        .selectAll(".label")
        .data(root.descendants())
        .join(enter => enter.append("text").attr("opacity", 0))
        .attr("class", "label")
        .attr("x", node => node.y)
        .attr("y", node => node.x - 12)
        .attr("text-anchor", "middle")
        .attr("font-size", 24)
        .text(node => node.data.name)
        .transition()
        .duration(500)
        .delay(node => node.depth * 300)
        .attr("opacity", 1);
    }, [initialData, dimensions, previouslyRenderedData]);
  
    return (
      <div ref={wrapperRef} style={{ marginBottom: "2rem" }}>
        <svg ref={svgRef}></svg>
      </div>
    );
  }
  
  export default TreeChart;